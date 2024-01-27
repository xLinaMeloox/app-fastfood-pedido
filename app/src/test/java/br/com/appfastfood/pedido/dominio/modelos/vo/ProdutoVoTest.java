package br.com.appfastfood.pedido.dominio.modelos.vo;

import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoVOTest {

    private ProdutoVO produtoVO;

    @BeforeEach
    void setUp() {
        produtoVO = new ProdutoVO("123", "2", "Produto Teste", 10.0, "Categoria Teste", "imagem.jpg");
    }

    @Test
    void testGetNome() {
        assertEquals("Produto Teste", produtoVO.getNome());
    }

    @Test
    void testGetPreco() {
        assertEquals(10.0, produtoVO.getPreco());
    }

    @Test
    void testGetCategoria() {
        assertEquals("Categoria Teste", produtoVO.getCategoria());
    }

    @Test
    void testGetUriImagem() {
        assertEquals("imagem.jpg", produtoVO.getUriImagem());
    }

    @Test
    void testSetNome() {
        produtoVO.setNome("Novo Nome");
        assertEquals("Novo Nome", produtoVO.getNome());
    }

    @Test
    void testSetPreco() {
        produtoVO.setPreco(15.0);
        assertEquals(15.0, produtoVO.getPreco());
    }

    @Test
    void testSetCategoria() {
        produtoVO.setCategoria("Nova Categoria");
        assertEquals("Nova Categoria", produtoVO.getCategoria());
    }

    @Test
    void testSetUriImagem() {
        produtoVO.setUriImagem("nova-imagem.jpg");
        assertEquals("nova-imagem.jpg", produtoVO.getUriImagem());
    }

    @Test
    void testGetIdProduto() {
        assertEquals("123", produtoVO.getIdProduto());
    }

    @Test
    void testGetQuantidadeProduto() {
        assertEquals("2", produtoVO.getQuantidadeProduto());
    }

    @Test
    void testSetIdProduto() {
        produtoVO.setIdProduto("456");
        assertEquals("456", produtoVO.getIdProduto());
    }

    @Test
    void testSetQuantidadeProduto() {
        produtoVO.setQuantidadeProduto("3");
        assertEquals("3", produtoVO.getQuantidadeProduto());
    }
}
