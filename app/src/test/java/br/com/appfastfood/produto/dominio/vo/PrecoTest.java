package br.com.appfastfood.produto.dominio.vo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrecoTest {

    @Test
    public void testGetPreco_DeveRetornarPreco() {
        // Arrange
        Double preco = 100.0;

        // Act
        Preco precoObj = new Preco(preco);

        // Assert
        assertEquals(preco, precoObj.getPreco());
    }
}
