package br.com.appfastfood.pedido.infraestrutura.entidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdEntTest {

    @Test
    public void testConstrutorComParametros_DeveRetornarObjetoCorreto() {
        // Arrange
        String idProduto = "idProduto";
        String quantidadeProduto = "quantidadeProduto";

        // Act
        ProdEnt prodEnt = new ProdEnt(idProduto, quantidadeProduto);

        // Assert
        assertEquals(idProduto, prodEnt.getIdProduto());
        assertEquals(quantidadeProduto, prodEnt.getQuantidadeProduto());
    }

    @Test
    public void testConstrutorPadrao_DeveRetornarObjetoCorreto() {
        // Act
        ProdEnt prodEnt = new ProdEnt();

        // Assert
        assertNull(prodEnt.getIdProduto());
        assertNull(prodEnt.getQuantidadeProduto());
    }
}
