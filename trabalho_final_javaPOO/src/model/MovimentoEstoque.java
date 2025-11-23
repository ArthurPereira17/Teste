package model;
import java.time.LocalDate;

public abstract class MovimentoEstoque implements Comparable<MovimentoEstoque>{
    private LocalDate data;
    private Produto produto;
    private int quantidade;
    
    public MovimentoEstoque(LocalDate data, Produto produto, int quantidade) {
        this.data = data;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(MovimentoEstoque outro) {
        return this.data.compareTo(outro.getData());
    }

    
}
