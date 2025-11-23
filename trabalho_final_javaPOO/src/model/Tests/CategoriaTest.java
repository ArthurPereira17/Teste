package model.Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.categorias.*;

public class CategoriaTest {

    @Test
    void testCategorias() {
        Categoria hardware = new ComponenteHardware();
        Categoria periferico = new Periferico();
        Categoria acessorio = new Acessorio();
        Categoria outro = new OutroProduto();

        assertEquals("Componente de Hardware", hardware.getNome());
        assertEquals("Periférico", periferico.getNome());
        assertEquals("Acessorio", acessorio.getNome());
        assertEquals("Outro produto", outro.getNome());
    }

    @Test
    void testSetterCategoria() {
        Categoria categoria = new ComponenteHardware();
        categoria.setNome("Hardware Component");
        assertEquals("Hardware Component", categoria.getNome());
    }
}