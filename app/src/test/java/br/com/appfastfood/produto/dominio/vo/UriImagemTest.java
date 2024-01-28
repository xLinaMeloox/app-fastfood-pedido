package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UriImagemTest {

    @Test
    public void testIsValid_UriImagemValida_DeveRetornarUriImagem() {
        // Arrange
        String uriImagem = "http://exemplo.com/imagem.jpg";

        // Act
        UriImagem uriImagemObj = new UriImagem(uriImagem);

        // Assert
        assertEquals(uriImagem, uriImagemObj.getUriImagem());
    }

    @Test
    public void testIsValid_UriImagemInvalida_DeveLancarExcecao() {
        // Arrange
        String uriImagem = "exemplo.com/imagem.jpg";

        // Act & Assert
        assertThrows(BadRequestException.class, () -> {
            new UriImagem(uriImagem);
        });
    }
}
