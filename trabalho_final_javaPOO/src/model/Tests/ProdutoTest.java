package model.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Produto;
import model.categorias.ComponenteHardware;

public class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("SSD 500GB", 250.00, 10, new ComponenteHardware());
    }

    @Test
    void testCriacaoProduto() {
        assertNotNull(produto);
        assertEquals("SSD 500GB", produto.getNome());
        assertEquals(250.00, produto.getPrecoUnitario(), 0.001);
        assertEquals(10, produto.getQuantidade());
        assertEquals("Componente de Hardware", produto.getCategoria().getNome());
    }

    @Test
    void testCalcularValorTotal() {
        double valorTotal = produto.calcularValorTotal();
        assertEquals(2500.00, valorTotal, 0.001);
    }

    @Test
    void testAdicionarQuantidade() {
        produto.adicionarQuantidade(5);
        assertEquals(15, produto.getQuantidade());
    }

    @Test
    void testRemoverQuantidade() {
        produto.removerQuantidade(3);
        assertEquals(7, produto.getQuantidade());
    }

    @Test
    void testRemoverQuantidadeInvalida() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produto.removerQuantidade(15);
        });
        assertEquals("Quantidade a remover maior que estoque disponível", exception.getMessage());
    }

    @Test
    void testGetNomeCategoria() {
        assertEquals("Componente de Hardware", produto.getNomeCategoria());
    }

    @Test
    void testCodigoAutoIncrement() {
        Produto p1 = new Produto("Produto 1", 100.0, 5, new ComponenteHardware());
        Produto p2 = new Produto("Produto 2", 200.0, 3, new ComponenteHardware());

        assertTrue(p2.getCodigo() > p1.getCodigo());
    }
}