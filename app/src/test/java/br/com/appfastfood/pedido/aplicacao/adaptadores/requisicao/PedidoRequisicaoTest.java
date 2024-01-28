package br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoRequisicaoTest {

    @Test
    void testPedidoRequisicao_DeveRetornarObjetoCorreto() {
        // Arrange
        List<ProdutosReq> produtos = new ArrayList<>();
        produtos.add(new ProdutosReq("Produto1", "10", "Nome do Produto1", 10.0, "Categoria do Produto1", "uriImagem1"));
        produtos.add(new ProdutosReq("Produto2", "20", "Nome do Produto2", 20.0, "Categoria do Produto2", "uriImagem2"));
        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(produtos, "123456", 30.0, "Em preparação", "30 minutos", "1", "Aprovado");

        // Act
        PedidoRequisicao pedidoRequisicaoRetornado = pedidoRequisicao;

        // Assert
        assertNotNull(pedidoRequisicaoRetornado);
        assertEquals(produtos, pedidoRequisicaoRetornado.getProdutos());
        assertEquals("123456", pedidoRequisicaoRetornado.getIdCliente());
        assertEquals(30.0, pedidoRequisicaoRetornado.getValorTotal());
        assertEquals("Em preparação", pedidoRequisicaoRetornado.getStatus());
        assertEquals("30 minutos", pedidoRequisicaoRetornado.getTempoEspera());
        assertEquals("1", pedidoRequisicaoRetornado.getIdPedido());
        assertEquals("Aprovado", pedidoRequisicaoRetornado.getStatusPagamento());
    }
}
