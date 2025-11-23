package model;
import java.time.LocalDate;

public class EntradaEstoque extends MovimentoEstoque{
    private double valorUnitario;
    public EntradaEstoque(LocalDate data, Produto produto, int quantidade, double valorUnitario) {
        super(data, produto, quantidade);
        this.valorUnitario = valorUnitario;
    }
    public double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}
