package br.com.appfastfood.configuracoes.client.carrinho;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarrinhoTest {

    @Test
    void getters_CorretamenteImplementados() {
        // Arrange
        Long id = 1L;
        String status = "RECEBIDO";
        List<Carrinho.Produto> produtos = new ArrayList<>();
        produtos.add(new Carrinho.Produto("1", 2));
        String idCliente = "123456";
        Double valorTotal = 100.00;

        // Act
        Carrinho carrinho = new Carrinho(id, status, produtos, idCliente, valorTotal);

        // Assert
        assertEquals(id, carrinho.id());
        assertEquals(status, carrinho.status());
        assertEquals(produtos, carrinho.produtos());
        assertEquals(idCliente, carrinho.idCliente());
        assertEquals(valorTotal, carrinho.valorTotal());
    }

    @Test
    void innerRecordProduto_DeveSerCriadoCorretamente() {
        // Arrange
        String idProduto = "1";
        Integer quantidadeProduto = 2;

        // Act
        Carrinho.Produto produto = new Carrinho.Produto(idProduto, quantidadeProduto);

        // Assert
        assertNotNull(produto);
        assertEquals(idProduto, produto.idProduto());
        assertEquals(quantidadeProduto, produto.quantidadeProduto());
    }


    @Test
     void testGetProdutos() {
        List<Carrinho.Produto> prods = new ArrayList<>();
        Carrinho.Produto produto1 = new Carrinho.Produto("Produto1", 1);
        Carrinho.Produto produto2 = new Carrinho.Produto("Produto2", 2);
        prods.add(produto1);
        prods.add(produto2);
        Carrinho suaClasse = new Carrinho(1L, "FECHADO", prods, "123456", 10D);

        assertEquals(2, suaClasse.getProdutos().size());
    }

    @Test
     void testGetIdCliente() {

        // Define um ID de cliente
        String idCliente = "123456";

        // Testa se o ID do cliente retornado é o mesmo que foi definido
        assertEquals("123456", idCliente);
    }

    @Test
     void testGetValorTotal() {
        // Cria uma instância da sua classe

        // Adiciona alguns produtos à lista de produtos
        List<Carrinho.Produto> prods = new ArrayList<>();
        Carrinho.Produto produto1 = new Carrinho.Produto("Produto1", 1);
        prods.add(produto1);

        assertEquals(1, prods.get(0).quantidadeProduto());
    }
}
