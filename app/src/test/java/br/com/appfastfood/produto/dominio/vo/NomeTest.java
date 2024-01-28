package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NomeTest {

    @Test
    public void testIsValid_NomeValido_DeveRetornarNome() {
        // Arrange
        String nome = "Nome válido";

        // Act
        Nome nomeObj = new Nome(nome);

        // Assert
        assertEquals(nome, nomeObj.getNome());
    }

    @Test
    public void testIsValid_NomeInvalido_DeveLancarExcecao() {
        // Arrange
        String nome = "";

        // Act & Assert
        assertThrows(BadRequestException.class, () -> {
            new Nome(nome);
        });
    }
}
