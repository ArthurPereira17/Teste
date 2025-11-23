package model.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import model.*;

public class ControleEstoqueTest {

    private ControleEstoque controle;
    private Produto produto;

    @BeforeEach
    void setUp() {
        // Limpa arquivos de teste anteriores
        new File("produtos.csv").delete();
        new File("movimentos.csv").delete();

        controle = new ControleEstoque();
        produto = new Produto("Teclado Mecânico", 150.0, 10, new model.categorias.Periferico());
    }

    @Test
    void testAdicionarProduto() {
        controle.adicionarProduto(produto);
        List<Produto> produtos = controle.getTodosProdutos();

        assertEquals(1, produtos.size());
        assertEquals("Teclado Mecânico", produtos.get(0).getNome());
    }

    @Test
    void testFindProdutoPorCodigo() {
        controle.adicionarProduto(produto);
        Produto encontrado = controle.findProdutoPorCodigo(produto.getCodigo());

        assertNotNull(encontrado);
        assertEquals(produto.getCodigo(), encontrado.getCodigo());
        assertEquals("Teclado Mecânico", encontrado.getNome());
    }

    @Test
    void testFindProdutoPorCodigoNaoExistente() {
        Produto encontrado = controle.findProdutoPorCodigo(999);
        assertNull(encontrado);
    }

    @Test
    void testRegistrarEntradaEstoque() {
        controle.adicionarProduto(produto);
        int quantidadeInicial = produto.getQuantidade();

        EntradaEstoque entrada = new EntradaEstoque(LocalDate.now(), produto, 5, 140.0);
        controle.registrarEntrada(entrada);

        assertEquals(quantidadeInicial + 5, produto.getQuantidade());
    }

    @Test
    void testRegistrarSaidaEstoque() {
        controle.adicionarProduto(produto);
        int quantidadeInicial = produto.getQuantidade();

        SaidaEstoque saida = new SaidaEstoque(LocalDate.now(), produto, 3);
        controle.registrarSaida(saida);

        assertEquals(quantidadeInicial - 3, produto.getQuantidade());
    }

    @Test
    void testExcluirProduto() {
        controle.adicionarProduto(produto);
        assertEquals(1, controle.getTodosProdutos().size());

        controle.excluirProduto(0);
        assertTrue(controle.getTodosProdutos().isEmpty());
    }

    @Test
    void testExcluirProdutoIndiceInvalido() {
        controle.adicionarProduto(produto);

        // Não deve lançar exceção para índices inválidos
        controle.excluirProduto(-1);
        controle.excluirProduto(10);

        assertEquals(1, controle.getTodosProdutos().size());
    }

    @Test
    void testGetCategoriaPorNome() {
        assertNotNull(controle.getCategoriaPorNome("Componente de Hardware"));
        assertNotNull(controle.getCategoriaPorNome("Periférico"));
        assertNotNull(controle.getCategoriaPorNome("Acessorio"));
        assertNotNull(controle.getCategoriaPorNome("Outro Produto"));
        assertNull(controle.getCategoriaPorNome("Categoria Inexistente"));
        assertNull(controle.getCategoriaPorNome(null));
    }

    @Test
    void testCalcularSaldoAtual() {
        controle.adicionarProduto(produto);

        SaldoCalculator.ResultadoSaldo saldo = controle.calcularSaldoAtual();
        assertNotNull(saldo);
        assertTrue(saldo.getValorTotal() >= 0);
    }
}