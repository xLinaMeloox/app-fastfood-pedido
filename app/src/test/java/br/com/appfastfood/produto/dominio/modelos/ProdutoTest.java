package br.com.appfastfood.produto.dominio.modelos;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.vo.Descricao;
import br.com.appfastfood.produto.dominio.vo.Nome;
import br.com.appfastfood.produto.dominio.vo.Preco;
import br.com.appfastfood.produto.dominio.vo.UriImagem;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import br.com.appfastfood.produto.exceptions.ExceptionsMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void construtor_DeveCriarProdutoCorretamente() {
        // Arrange
        Long id = 1L;
        String nome = "Hamburguer";
        Double preco = 10.0;
        String uriImagem = "imagem.jpg";
        CategoriaEnum categoria = CategoriaEnum.lanche;
        String descricao = "Delicioso hamburguer";

        // Act
        Produto produto = new Produto(id, nome, preco, uriImagem, categoria, descricao);

        // Assert
        assertEquals(id, produto.getId());
        assertEquals(nome, produto.getNome());
        assertEquals(preco, produto.getPreco());
        assertEquals(uriImagem, produto.getUriImagem());
        assertEquals(categoria, produto.getCategoria());
        assertEquals(descricao, produto.getDescricao());
    }

    @Test
    void validarCampos_QuandoCamposSaoValidos_NaoDeveLancarExcecao() {
        // Arrange
        Nome nome = new Nome("Hamburguer");
        Preco preco = new Preco(10.0);
        UriImagem uriImagem = new UriImagem("http://teste.com");
        CategoriaEnum categoria = CategoriaEnum.lanche;
        Descricao descricao = new Descricao("Delicioso hamburguer");

        Produto produto = new Produto(null, null, null, null, null, null);

        // Act & Assert
        assertDoesNotThrow(() -> produto.validarCampos(nome, preco, uriImagem, categoria, descricao));
    }

    @Test
    void validarCampos_QuandoCamposSaoNulos_DeveLancarExcecao() {
        // Arrange
        Produto produto = new Produto(null, null, null, null, null, null);
        Nome nome = new Nome("Hamburguer");
        Preco preco = new Preco(10.0);
        UriImagem uriImagem = new UriImagem("http://teste.com");
        CategoriaEnum categoria = CategoriaEnum.lanche;
        Descricao descricao = new Descricao("Delicioso hamburguer");
        // Act & Assert
        assertDoesNotThrow(() -> produto.validarCampos(nome, preco, uriImagem, categoria, descricao));
    }
}
