package view;

import javax.swing.*;
import java.awt.*;

public class PainelLateral extends JPanel {
    private JButton btnProdutos;
    private JButton btnEntrada;
    private JButton btnSaida;
    private JButton btnSaldo;
    private JButton btnMovimentos;

    public PainelLateral() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnProdutos = criarBotao("Produtos");
        btnEntrada = criarBotao("Registrar Entrada");
        btnSaida = criarBotao("Registrar Saída");
        btnSaldo = criarBotao("Consultar Saldo");
        btnMovimentos = criarBotao("Listar Movimentos");

        add(btnProdutos);
        add(Box.createVerticalStrut(8));
        add(btnEntrada);
        add(Box.createVerticalStrut(8));
        add(btnSaida);
        add(Box.createVerticalStrut(8));
        add(btnSaldo);
        add(Box.createVerticalStrut(8));
        add(btnMovimentos);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);

        botao.setAlignmentX(CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(150, 35));

        return botao;
    }

    public void onProdutos(Runnable r) { btnProdutos.addActionListener(e -> r.run()); }
    public void onEntrada(Runnable r) { btnEntrada.addActionListener(e -> r.run()); }
    public void onSaida(Runnable r) { btnSaida.addActionListener(e -> r.run()); }
    public void onSaldo(Runnable r) { btnSaldo.addActionListener(e -> r.run()); }
    public void onMovimentos(Runnable r) { btnMovimentos.addActionListener(e -> r.run()); 
    }
}