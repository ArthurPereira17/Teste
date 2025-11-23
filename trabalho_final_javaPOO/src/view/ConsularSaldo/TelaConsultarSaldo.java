package view.ConsularSaldo;

import model.ControleEstoque;
import model.SaldoCalculator;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TelaConsultarSaldo extends JPanel {

    private ControleEstoque controle;
    private JLabel lblQuantidade;
    private JLabel lblValorTotal;
    private JLabel lblCustoMedio;

    public TelaConsultarSaldo(ControleEstoque controle) {
        this.controle = controle;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Consulta de Saldo Atual do Estoque");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel painelInfo = new JPanel(new GridLayout(3, 2, 10, 10));

        painelInfo.add(new JLabel("Quantidade Total:"));
        lblQuantidade = new JLabel("-");
        painelInfo.add(lblQuantidade);

        painelInfo.add(new JLabel("Valor Total (R$):"));
        lblValorTotal = new JLabel("-");
        painelInfo.add(lblValorTotal);

        painelInfo.add(new JLabel("Custo Médio (R$):"));
        lblCustoMedio = new JLabel("-");
        painelInfo.add(lblCustoMedio);

        add(painelInfo, BorderLayout.CENTER);

        JButton btnAtualizar = new JButton("Atualizar Saldo");
        btnAtualizar.addActionListener(e -> atualizarSaldo());
        add(btnAtualizar, BorderLayout.SOUTH);
    }

    public void atualizarSaldo() {
        SaldoCalculator.ResultadoSaldo saldo = controle.calcularSaldoAtual();

        // Soma a quantidade total de todos os produtos
        int quantidadeTotal = 0;
        for (Map.Entry<Produto, Integer> entry : saldo.getQuantidadesPorProduto().entrySet()) {
            quantidadeTotal += entry.getValue();
        }

        // Valor total
        double valorTotal = saldo.getValorTotal();

        // Custo médio
        double custoMedio = quantidadeTotal > 0 ? valorTotal / quantidadeTotal : 0.0;

        lblQuantidade.setText(String.valueOf(quantidadeTotal));
        lblValorTotal.setText(String.format("%.2f", valorTotal));
        lblCustoMedio.setText(String.format("%.2f", custoMedio));
    }
}
