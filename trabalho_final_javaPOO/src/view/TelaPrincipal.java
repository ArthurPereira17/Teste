package view;

import javax.swing.*;

import model.ControleEstoque;
import view.CadastroProduto.TelaCadastroProdutos;
import view.ConsularSaldo.TelaConsultarSaldo;
import view.ListarMovimentos.TelaListarMovimentos;
import view.RegistrarEntrada.TelaRegistrarEntrada;
import view.RegistrarSaida.TelaRegistrarSaida;

import java.awt.*;

public class TelaPrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel painelConteudo;

    private ControleEstoque controleEstoque = new ControleEstoque();

    private TelaListarMovimentos telaListarMovimentos = new TelaListarMovimentos(controleEstoque);
    private TelaConsultarSaldo telaConsultarSaldo = new TelaConsultarSaldo(controleEstoque);
    private TelaCadastroProdutos telaProdutos = new TelaCadastroProdutos();

    public TelaPrincipal() {
        setTitle("Sistema loja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        PainelLateral painelLateral = new PainelLateral();
        add(painelLateral, BorderLayout.WEST);

        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);

        painelConteudo.add(telaProdutos, "produtos");
        painelConteudo.add(telaListarMovimentos, "movimentos");
        painelConteudo.add(new TelaRegistrarEntrada(controleEstoque, telaListarMovimentos), "registrarEntrada");
        painelConteudo.add(new TelaRegistrarSaida(controleEstoque, telaListarMovimentos), "registrarSaida");
        painelConteudo.add(telaConsultarSaldo, "saldo");

        add(painelConteudo, BorderLayout.CENTER);

        painelLateral.onProdutos(() -> {
            telaProdutos.getPainelTabelaProdutos()
                    .atualizarTabela(controleEstoque.getTodosProdutos());
            mostrarTela("produtos");
        });

        painelLateral.onMovimentos(() -> {
            telaListarMovimentos.carregarMovimentosDoCSV();
            mostrarTela("movimentos");
        });

        painelLateral.onEntrada(() -> mostrarTela("registrarEntrada"));

        painelLateral.onSaida(() -> {
            telaListarMovimentos.carregarMovimentosDoCSV();
            mostrarTela("registrarSaida");
        });

        painelLateral.onSaldo(() -> {
            telaConsultarSaldo.atualizarSaldo();
            mostrarTela("saldo");
        });
    }

    private void mostrarTela(String nome) {
        cardLayout.show(painelConteudo, nome);
    }
}
