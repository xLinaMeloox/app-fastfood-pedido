package br.com.appfastfood.pedido.infraestrutura.entidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomSequenceTest {

    @Test
    public void testConstructorWithParameters_DeveRetornarObjetoCorreto() {
        // Arrange
        String id = "id";
        Long sequence = 1L;

        // Act
        CustomSequence customSequence = new CustomSequence(id, sequence);

        // Assert
        assertEquals(id, customSequence.getId());
        assertEquals(sequence, customSequence.getSequence());
    }

    @Test
    public void testDefaultConstructor_DeveRetornarObjetoCorreto() {
        // Act
        CustomSequence customSequence = new CustomSequence();

        // Assert
        assertNull(customSequence.getId());
        assertNull(customSequence.getSequence());
    }
}