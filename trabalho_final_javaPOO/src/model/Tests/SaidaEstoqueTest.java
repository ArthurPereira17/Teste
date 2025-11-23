package model.Tests;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import model.SaidaEstoque;
import model.Produto;
import model.categorias.Periferico;

public class SaidaEstoqueTest {

    @Test
    void testCriacaoSaidaEstoque() {
        Produto produto = new Produto("Monitor 24\"", 900.0, 5, new Periferico());
        LocalDate data = LocalDate.of(2025, 11, 18);

        SaidaEstoque saida = new SaidaEstoque(data, produto, 2);

        assertEquals(data, saida.getData());
        assertEquals(produto, saida.getProduto());
        assertEquals(2, saida.getQuantidade());
    }
}