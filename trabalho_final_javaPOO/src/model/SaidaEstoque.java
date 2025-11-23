package model;
import java.time.LocalDate;

public class SaidaEstoque extends MovimentoEstoque{

    public SaidaEstoque(LocalDate data, Produto produto, int quantidade) {
        super(data, produto, quantidade);
    }

}
