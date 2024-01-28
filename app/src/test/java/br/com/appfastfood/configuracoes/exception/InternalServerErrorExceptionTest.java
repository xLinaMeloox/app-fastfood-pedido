package br.com.appfastfood.configuracoes.execption;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InternalServerErrorExceptionTest {

    @Test
    public void testConstrutorPadrao_DeveRetornarObjetoCorreto() {
        // Act
        InternalServerErrorException exception = new InternalServerErrorException();

        // Assert
        assertNotNull(exception);
        assertEquals(InternalServerErrorException.MESSAGER, exception.getMessage());
    }
}