package model;
import model.categorias.Categoria;

public class Produto {
    private static int proximoCodigo = 1;
    private int codigo;
    private String nome;
    private double precoUnitario;
    private int quantidade;
    private Categoria categoria;

    public Produto(String nome, double precoUnitario, int quantidade, Categoria categoria) {
        this.codigo = proximoCodigo++;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public Produto(int codigo, String nome, double precoUnitario, int quantidade, Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.categoria = categoria;

        // Atualiza o contador global corretamente
        if (codigo >= proximoCodigo) {
            proximoCodigo = codigo + 1;
        }
    }
    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double calcularValorTotal(){
        return quantidade * precoUnitario;
    }
    
    public String getNomeCategoria(){
        return categoria.getNome();
    }

    // Adicione estes métodos na classe Produto (antes do fechamento da classe)
    public void adicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public void removerQuantidade(int quantidade) {
        if (quantidade > this.quantidade) {
            throw new IllegalArgumentException("Quantidade a remover maior que estoque disponível");
        }
        this.quantidade -= quantidade;
    }

    public int getQuantidadeEstoque() {
        return quantidade;
    }
}
