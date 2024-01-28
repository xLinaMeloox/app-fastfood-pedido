package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;


public class CategoriaTest {

    @Test
    public void testIsValid_CategoriaValida_DeveRetornarCategoria() {
        // Arrange
        String categoria = "bebida";

        // Act
        Categoria categoriaObj = new Categoria(categoria);

        // Assert
        assertEquals(CategoriaEnum.bebida, categoriaObj.getCategoria());
    }

    @Test
    public void testIsValid_CategoriaInvalida_DeveLancarExcecao() {
        // Arrange
        String categoria = "CATEGORIA_INVALIDA";

        // Act & Assert
        assertThrows(BadRequestException.class, () -> {
            new Categoria(categoria);
        });
    }
}