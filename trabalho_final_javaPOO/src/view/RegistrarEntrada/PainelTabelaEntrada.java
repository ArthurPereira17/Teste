package view.RegistrarEntrada;

import model.EntradaEstoque;
import model.MovimentoEstoque;
import model.ControleEstoque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelTabelaEntrada extends JPanel {

    private JTable tabela;
    private DefaultTableModel modelo;

    public PainelTabelaEntrada() {
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Data", "Produto", "Quantidade", "Valor Unit."}, 0);
        tabela = new JTable(modelo);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    public void carregarEntradas(List<MovimentoEstoque> movimentos) {
        modelo.setRowCount(0);

        for (MovimentoEstoque m : movimentos) {
            if (m instanceof EntradaEstoque entrada) {
                modelo.addRow(new Object[]{
                        entrada.getData(),
                        entrada.getProduto().getNome(),
                        entrada.getQuantidade(),
                        entrada.getValorUnitario()
                });
            }
        }
    }
}
