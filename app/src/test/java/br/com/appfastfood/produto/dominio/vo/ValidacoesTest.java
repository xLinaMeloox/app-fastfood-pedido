package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidacoesTest {

    @Test
    public void testValidaTamanhoMaximoDoCampo_CampoMaiorQueILimit_DeveRetornarTrue() {
        // Arrange
        String campo = "campo maior que o limite";
        int limit = 10;

        // Act
        boolean result = Validacoes.validaTamanhoMaximoDoCampo(campo, limit);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testValidaTamanhoMaximoDoCampo_CampoMenorOUIgualALimit_DeveRetornarFalse() {
        // Arrange
        String campo = "campo";
        int limit = 10;

        // Act
        boolean result = Validacoes.validaTamanhoMaximoDoCampo(campo, limit);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testValidaCamposVaziosOuNulos_CamposVaziosOuNulos_DeveRetornarTrue() {
        // Arrange
        Nome nome = new Nome("a");
        Preco preco = new Preco(null);
        UriImagem uriImagem = new UriImagem("http://aaaa.com");
        CategoriaEnum categoria = null;
        Descricao descricao = new Descricao("");

        // Act
        boolean result = Validacoes.validaCamposVaziosOuNulos(nome, preco, uriImagem, categoria, descricao);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testValidaCamposVaziosOuNulos_CamposNaoVaziosNaoNulos_DeveRetornarFalse() {
        // Arrange
        Nome nome = new Nome("Nome");
        Preco preco = new Preco(100.0);
        UriImagem uriImagem = new UriImagem("http://exemplo.com/imagem.jpg");
        CategoriaEnum categoria = CategoriaEnum.bebida;
        Descricao descricao = new Descricao("Descrição");

        // Act
        boolean result = Validacoes.validaCamposVaziosOuNulos(nome, preco, uriImagem, categoria, descricao);

        // Assert
        assertFalse(result);
    }
}
