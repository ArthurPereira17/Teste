package view.RegistrarEntrada;

import model.ControleEstoque;
import model.EntradaEstoque;
import model.Produto;
import view.ListarMovimentos.TelaListarMovimentos;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaRegistrarEntrada extends JPanel {

    private ControleEstoque controle;
    private TelaListarMovimentos telaListarMovimentos;

    public TelaRegistrarEntrada(ControleEstoque controle, TelaListarMovimentos telaMovimentos) {
        this.controle = controle;
        this.telaListarMovimentos = telaMovimentos;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Registrar Entrada no Estoque");
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

        JLabel lblQuantidade = new JLabel("Quantidade:");
        JTextField txtQuantidade = new JTextField();

        JLabel lblData = new JLabel("Data de entrada:");
        JTextField txtData = new JTextField();

        JLabel lblValor = new JLabel("Valor Unitário:");
        JTextField txtValor = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Entrada");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));

        txtCodigo.setPreferredSize(new Dimension(200, 30));
        txtData.setPreferredSize(new Dimension(200, 30));
        txtQuantidade.setPreferredSize(new Dimension(200, 30));
        txtValor.setPreferredSize(new Dimension(200, 30));

        // Adicionando ao painel
        c.gridx = 0;
        c.gridy = 0;
        painelForm.add(lblCodigo, c);
        c.gridx = 1;
        painelForm.add(txtCodigo, c);

        c.gridx = 0;
        c.gridy = 1;
        painelForm.add(lblQuantidade, c);
        c.gridx = 1;
        painelForm.add(txtQuantidade, c);

        c.gridx = 0;
        c.gridy = 2;
        painelForm.add(lblData, c);
        c.gridx = 1;
        painelForm.add(txtData, c);

        c.gridx = 0;
        c.gridy = 3;
        painelForm.add(lblValor, c);
        c.gridx = 1;
        painelForm.add(txtValor, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        painelForm.add(btnRegistrar, c);

        add(painelForm, BorderLayout.CENTER);

        PainelTabelaEntrada painelEntradas = new PainelTabelaEntrada();
        add(painelEntradas, BorderLayout.SOUTH);

        painelEntradas.carregarEntradas(controle.getMovimentoEstoques());

        // Lógica do botão
        btnRegistrar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                int quantidadeEntrada = Integer.parseInt(txtQuantidade.getText());
                double valorUnitario = Double.parseDouble(txtValor.getText());

                Produto p = controle.getTodosProdutos()
                        .stream()
                        .filter(prod -> prod.getCodigo() == codigo)
                        .findFirst()
                        .orElse(null);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                    return;
                }

                // ======= VALORES ANTES DA ENTRADA ========
                int quantidadeAntes = p.getQuantidade();
                double valorAntes = quantidadeAntes * p.getPrecoUnitario();

                // Registrar a entrada
                EntradaEstoque entrada = new EntradaEstoque(
                        LocalDate.now(),
                        p,
                        quantidadeEntrada,
                        valorUnitario);

                controle.registrarEntrada(entrada);

                // ======= VALORES DEPOIS DA ENTRADA ========
                int quantidadeDepois = p.getQuantidade();
                double valorDepois = quantidadeDepois * p.getPrecoUnitario();

                // Atualiza tabela
                painelEntradas.carregarEntradas(controle.getMovimentoEstoques());
                telaListarMovimentos.carregarMovimentosDoCSV();

                // ======= EXTRATO DO IMPACTO ========
                String msg = String.format(
                        "Entrada registrada!\n\n" +
                                "ANTES DA ENTRADA:\n" +
                                "- Quantidade: %d\n" +
                                "- Valor total: R$ %.2f\n\n" +
                                "DEPOIS DA ENTRADA:\n" +
                                "- Quantidade: %d\n" +
                                "- Valor total: R$ %.2f\n\n" +
                                "Impacto:\n" +
                                "+%d unidades adicionadas",
                        quantidadeAntes,
                        valorAntes,
                        quantidadeDepois,
                        valorDepois,
                        quantidadeEntrada);

                JOptionPane.showMessageDialog(this, msg, "Impacto no Saldo", JOptionPane.INFORMATION_MESSAGE);

                // Limpar campos
                txtCodigo.setText("");
                txtQuantidade.setText("");
                txtValor.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!");
            }
        });
    }
}
