package view.CadastroProduto;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ControleEstoque;
import model.Produto;

import model.categorias.Categoria;


public class PainelFormularioProduto extends JPanel {
    private JTextField campoNome;
    private JTextField campoPrecoUni;
    private JTextField campoQuantidade;
    private JComboBox<String> comboCategoria;
    private JButton botaoCadastrar;

    public PainelFormularioProduto() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Cadastro de Produtos"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel rotuloNome = new JLabel("Nome:");
        JLabel rotuloPrecoUni = new JLabel("Preco Unitário:");
        JLabel rotuloQuantidade = new JLabel("Quantidade:");
        JLabel rotuloCategoria = new JLabel("Categoria:");

        
        campoNome = new JTextField(20);
        campoPrecoUni = new JTextField(8);
        campoQuantidade = new JTextField(4);

        String[] categorias = { "Acessorio", "Componente de Hardware",
                "Periférico", "Outro Produto" };

        comboCategoria = new JComboBox<>(categorias);

        botaoCadastrar = new JButton("Salvar");


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(rotuloNome, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(campoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(rotuloCategoria, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(comboCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        add(rotuloPrecoUni, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(campoPrecoUni, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        add(rotuloQuantidade, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(campoQuantidade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botaoCadastrar, gbc);

    }

    public void definirAcaoBotaoCadastrar(ActionListener acao) {
        botaoCadastrar.addActionListener(acao);
    }

    public Produto obterProdutoDoForulario(ControleEstoque controle) {

        String nome = campoNome.getText().trim();
        String textoPrecoUni = campoPrecoUni.getText().trim();
        String textoQuantidade = campoQuantidade.getText().trim();
        String nomeCategoria = (String) comboCategoria.getSelectedItem();

        if (nome.isEmpty() || textoPrecoUni.isEmpty() || textoQuantidade.isEmpty() || nomeCategoria == null) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos obrigatórios.",
                    "Campos Incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {

        double precoUni = Double.parseDouble(textoPrecoUni);
        int quantidade = Integer.parseInt(textoQuantidade);

        
        Categoria categoriaObj = controle.getCategoriaPorNome(nomeCategoria);

        if (categoriaObj == null) {
            JOptionPane.showMessageDialog(this,
                    "Categoria inválida selecionada.",
                    "Erro de Categoria",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Produto(nome, precoUni, quantidade, categoriaObj);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "Os campos ID, Preço Unitário e Quantidade devem conter números válidos.",
                "Erro de Formato",
                JOptionPane.ERROR_MESSAGE);
        return null;
    }
    }

    public void limparFormulario() {

		campoNome.setText("");
		campoPrecoUni.setText("");
        campoQuantidade.setText("");
		comboCategoria.setSelectedIndex(0);
        campoNome.requestFocus();
	}
}
