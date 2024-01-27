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
        String status = "Em andamento";
        List<Carrinho.Produto> produtos = new ArrayList<>();
        produtos.add(new Carrinho.Produto("1", 2));
        String idCliente = "cliente123";
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
}
