package view.RegistrarSaida;

import model.ControleEstoque;
import model.SaidaEstoque;
import model.Produto;
import view.ListarMovimentos.TelaListarMovimentos;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaRegistrarSaida extends JPanel {

    private ControleEstoque controle;
    private TelaListarMovimentos telaListarMovimentos;

    public TelaRegistrarSaida(ControleEstoque controle, TelaListarMovimentos telaListarMovimentos) {
        this.controle = controle;
        this.telaListarMovimentos = telaListarMovimentos;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Registrar Saída do Estoque");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        // Painel central
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        JLabel lblCodigo = new JLabel("Código do Produto:");
        JTextField txtCodigo = new JTextField();

        JLabel lblData = new JLabel("Data (dd/MM/yyyy):");
        JTextField txtData = new JTextField();

        JLabel lblQuantidade = new JLabel("Quantidade:");
        JTextField txtQuantidade = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Saída");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));

        txtCodigo.setPreferredSize(new Dimension(220, 30));
        txtData.setPreferredSize(new Dimension(220, 30));
        txtQuantidade.setPreferredSize(new Dimension(220, 30));

        // Adicionando ao painel
        c.gridx = 0;
        c.gridy = 0;
        painelForm.add(lblCodigo, c);
        c.gridx = 1;
        painelForm.add(txtCodigo, c);

        c.gridx = 0;
        c.gridy = 1;
        painelForm.add(lblData, c);
        c.gridx = 1;
        painelForm.add(txtData, c);

        c.gridx = 0;
        c.gridy = 2;
        painelForm.add(lblQuantidade, c);
        c.gridx = 1;
        painelForm.add(txtQuantidade, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        painelForm.add(btnRegistrar, c);

        add(painelForm, BorderLayout.CENTER);

        PainelTabelaSaida painelSaidas = new PainelTabelaSaida();
        add(painelSaidas, BorderLayout.SOUTH);

        painelSaidas.carregarSaidas(controle.getMovimentoEstoques());

        // 👉 Lógica do botão
        btnRegistrar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText().trim());
                int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
                LocalDate data = parseDataDoCampo(txtData);

                if (data == null) return;
                if (quantidade <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero!");
                    return;
                }

                Produto p = controle.getTodosProdutos()
                        .stream()
                        .filter(prod -> prod.getCodigo() == codigo)
                        .findFirst()
                        .orElse(null);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                    return;
                }

                // Estoque insuficiente
                if (p.getQuantidade() < quantidade) {
                    JOptionPane.showMessageDialog(this, 
                            "Estoque insuficiente! Disponível: " + p.getQuantidade());
                    return;
                }

                // Registrar saída
                SaidaEstoque saida = new SaidaEstoque(data, p, quantidade);

                controle.registrarSaida(saida);
                painelSaidas.carregarSaidas(controle.getMovimentoEstoques());

                if (telaListarMovimentos != null) {
                    telaListarMovimentos.carregarMovimentosDoCSV();
                }

                // Mostrar impacto no saldo (igual entrada)
                Produto produto = saida.getProduto();
                int novaQuantidade = produto.getQuantidade();
                double valorTotal = produto.calcularValorTotal();

                String msg = String.format(
                        "Saída registrada com sucesso!\n\n" +
                        "Produto: %s\n" +
                        "Data: %s\n" +
                        "Quantidade saída: -%d\n\n" +
                        "Novo saldo (unidades): %d\n" +
                        "Valor total em estoque: R$ %.2f\n",
                        produto.getNome(),
                        saida.getData(),
                        saida.getQuantidade(),
                        novaQuantidade,
                        valorTotal
                );

                JOptionPane.showMessageDialog(this, msg, "Impacto no saldo", JOptionPane.INFORMATION_MESSAGE);

                // Limpar campos
                txtCodigo.setText("");
                txtQuantidade.setText("");
                txtData.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });
    }

    public static LocalDate parseDataDoCampo(JTextField txtData) {
        String dataStr = txtData.getText().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Formato de data inválido! Use dd/MM/yyyy.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
