package view.CadastroProduto;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ControleEstoque;
import model.Produto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroProdutos extends JPanel {
    private ControleEstoque controle;
    private PainelFormularioProduto painelFormulario;
    private PainelTabelaProdutos painelTabela;

    public TelaCadastroProdutos() {

        controle = new ControleEstoque();

        setLayout(new BorderLayout(10, 10));

        painelFormulario = new PainelFormularioProduto();
        painelTabela = new PainelTabelaProdutos();

        add(painelFormulario, BorderLayout.NORTH);
        add(painelTabela, BorderLayout.CENTER);

        painelTabela.atualizarTabela(controle.getTodosProdutos());
        painelFormulario.definirAcaoBotaoCadastrar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto produto = painelFormulario.obterProdutoDoForulario(controle);
                if (produto != null) {
                    controle.adicionarProduto(produto);
                    painelTabela.atualizarTabela(controle.getTodosProdutos());
                    painelFormulario.limparFormulario();
                }
            }
        });

        painelTabela.definirAcaoBotaoRemover(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = painelTabela.obterLinhaSelecionada();
                if (linhaSelecionada >= 0) {
                    controle.excluirProduto(linhaSelecionada);
                    painelTabela.atualizarTabela(controle.getTodosProdutos());
                }
            }
        });
    }

    public PainelTabelaProdutos getPainelTabelaProdutos() {
        return painelTabela;
    }
}
