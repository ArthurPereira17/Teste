package view.RegistrarSaida;

import model.SaidaEstoque;
import model.MovimentoEstoque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelTabelaSaida extends JPanel {

    private JTable tabela;
    private DefaultTableModel modelo;

    public PainelTabelaSaida() {
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Data", "Produto", "Quantidade"}, 0);
        tabela = new JTable(modelo);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    public void carregarSaidas(List<MovimentoEstoque> movimentos) {
        modelo.setRowCount(0);

        for (MovimentoEstoque m : movimentos) {
            if (m instanceof SaidaEstoque saida) {
                modelo.addRow(new Object[]{
                        saida.getData(),
                        saida.getProduto().getNome(),
                        saida.getQuantidade()
                });
            }
        }
    }
}
