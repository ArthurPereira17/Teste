package model.Tests;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import model.EntradaEstoque;
import model.Produto;
import model.categorias.ComponenteHardware;

public class EntradaEstoqueTest {

    @Test
    void testCriacaoEntradaEstoque() {
        Produto produto = new Produto("SSD NVMe", 350.0, 8, new ComponenteHardware());
        LocalDate data = LocalDate.of(2025, 11, 20);

        EntradaEstoque entrada = new EntradaEstoque(data, produto, 5, 320.0);

        assertEquals(data, entrada.getData());
        assertEquals(produto, entrada.getProduto());
        assertEquals(5, entrada.getQuantidade());
        assertEquals(320.0, entrada.getValorUnitario(), 0.001);
    }

    @Test
    void testSetterValorUnitario() {
        Produto produto = new Produto("Placa de Vídeo", 1200.0, 2, new ComponenteHardware());
        EntradaEstoque entrada = new EntradaEstoque(LocalDate.now(), produto, 1, 1100.0);

        entrada.setValorUnitario(1150.0);
        assertEquals(1150.0, entrada.getValorUnitario(), 0.001);
    }
}