package br.com.appfastfood.configuracoes.execption;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BadRequestExceptionTest {

    @Test
    public void testConstrutorComParametro_DeveRetornarObjetoCorreto() {
        // Arrange
        String mensagem = "Mensagem de erro";

        // Act
        BadRequestException exception = new BadRequestException(mensagem);

        // Assert
        assertNotNull(exception);
        assertEquals(mensagem, exception.getMessage());
    }
}