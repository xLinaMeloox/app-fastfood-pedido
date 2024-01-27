package br.com.appfastfood.pedido.aplicacao.adaptadores.resposta;

import br.com.appfastfood.pedido.aplicacao.adaptadores.resposta.PedidoResposta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class PedidoRespostaTest {

    private PedidoResposta pedidoResposta;

    @BeforeEach
    void setUp() {
        Map<String, Long> produtos = new HashMap<>();
        produtos.put("produto1", 1L);
        produtos.put("produto2", 2L);

        pedidoResposta = PedidoResposta.builder()
                .produto(produtos)
                .idCliente("1234")
                .valorTotal(BigDecimal.valueOf(50.0))
                .status("PRONTO")
                .tempoEspera("30")
                .build();
    }

    @Test
    void testProduto() {
        Map<String, Long> produtos = new HashMap<>();
        produtos.put("produto1", 1L);
        produtos.put("produto2", 2L);

        assertEquals(produtos, pedidoResposta.getProduto());
    }

    @Test
    void testIdCliente() {
        assertEquals("1234", pedidoResposta.getIdCliente());
    }

    @Test
    void testValorTotal() {
        assertEquals(BigDecimal.valueOf(50.0), pedidoResposta.getValorTotal());
    }

    @Test
    void testStatus() {
        assertEquals("PRONTO", pedidoResposta.getStatus());
    }

    @Test
    void testTempoEspera() {
        assertEquals("30", pedidoResposta.getTempoEspera());
    }

    @Test
    void testBuilder() {
        assertNotNull(PedidoResposta.builder());
    }
}
