package view.CadastroProduto;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import model.Produto;

public class PainelTabelaProdutos extends JPanel{
    private JTable tabela;
	private DefaultTableModel modeloTabela;
	private JButton botaoRemover;

    public PainelTabelaProdutos() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createTitledBorder("Produtos Cadastrados"));

        String[] colunas = {"Id","Nome","Preco Unitário","Quantidade","Categoria"};
        modeloTabela = new DefaultTableModel(colunas,0);

        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane painelRolagem = new JScrollPane(tabela);

        botaoRemover = new JButton("Remover Selecionado");

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        painelBotoes.add(botaoRemover);

        add(painelRolagem, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

    }

    public void atualizarTabela(List<Produto> produtos ){
        modeloTabela.setRowCount(0);

        for(Produto produto : produtos){
            Object[] linha = {produto.getCodigo() ,produto.getNome(), produto.getPrecoUnitario(), produto.getQuantidade(), produto.getNomeCategoria()};
            modeloTabela.addRow(linha);
        }
    }   

    public int obterLinhaSelecionada() {
		return tabela.getSelectedRow();
	}

	public void definirAcaoBotaoRemover(ActionListener acao) {
		botaoRemover.addActionListener(acao);
	}
}
