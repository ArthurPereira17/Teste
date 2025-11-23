package model.Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import model.MovimentoEstoque;
import model.Produto;
import model.EntradaEstoque;
import model.SaidaEstoque;
import model.categorias.ComponenteHardware;

public class MovimentoEstoqueTest {

    @Test
    void testComparacaoMovimentosPorData() {
        Produto produto = new Produto("Memória RAM", 200.0, 5, new ComponenteHardware());

        MovimentoEstoque movimento1 = new EntradaEstoque(LocalDate.of(2025, 1, 10), produto, 2, 180.0);
        MovimentoEstoque movimento2 = new EntradaEstoque(LocalDate.of(2025, 1, 15), produto, 3, 190.0);
        MovimentoEstoque movimento3 = new EntradaEstoque(LocalDate.of(2025, 1, 10), produto, 1, 185.0);

        // movimento1 deve ser anterior a movimento2
        assertTrue(movimento1.compareTo(movimento2) < 0);

        // movimento2 deve ser posterior a movimento1
        assertTrue(movimento2.compareTo(movimento1) > 0);

        // Datas iguais
        assertEquals(0, movimento1.compareTo(movimento3));
    }

    @Test
    void testGettersAndSettersMovimento() {
        Produto produto = new Produto("Processador", 800.0, 3, new ComponenteHardware());
        LocalDate data = LocalDate.now();

        MovimentoEstoque movimento = new EntradaEstoque(data, produto, 2, 750.0);

        assertEquals(data, movimento.getData());
        assertEquals(produto, movimento.getProduto());
        assertEquals(2, movimento.getQuantidade());

        // Test setters
        LocalDate novaData = LocalDate.of(2025, 12, 1);
        Produto novoProduto = new Produto("Novo Produto", 100.0, 1, new ComponenteHardware());

        movimento.setData(novaData);
        movimento.setProduto(novoProduto);
        movimento.setQuantidade(5);

        assertEquals(novaData, movimento.getData());
        assertEquals(novoProduto, movimento.getProduto());
        assertEquals(5, movimento.getQuantidade());
    }
}