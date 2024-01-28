package br.com.appfastfood.produto.dominio.vo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DescricaoTest {

    @Test
    public void testGetDescricao_DeveRetornarDescricao() {
        // Arrange
        String descricao = "Descrição do produto";

        // Act
        Descricao descricaoObj = new Descricao(descricao);

        // Assert
        assertEquals(descricao, descricaoObj.getDescricao());
    }
}