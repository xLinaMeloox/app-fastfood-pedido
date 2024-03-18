package br.com.appfastfood.pedido.dominio.modelos;

import br.com.appfastfood.configuracoes.client.pagamento.Pagamentos;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PedidoTest {

    @Test
    void construtorComListaDeProdutos_CorretamenteInicializado() {
        // Arrange
        List<ProdutoVO> produtos = new ArrayList<>();
        produtos.add(new ProdutoVO("1", "2"));
        produtos.add(new ProdutoVO("2", "1"));
        String cliente = "123456";
        Double valorTotal = 100.00;
        StatusPedidoEnum status = StatusPedidoEnum.RECEBIDO;
        String tempoEspera = "30";
        StatusPagamentoEnum statusPagamento = StatusPagamentoEnum.PENDENTE;

        // Act
        Pedido pedido = new Pedido(produtos, cliente, valorTotal, status, tempoEspera, statusPagamento);

        // Assert
        assertNotNull(pedido);
        assertEquals(produtos, pedido.getProdutos());
        assertEquals(cliente, pedido.getCliente());
        assertEquals(valorTotal, pedido.getValorTotal());
        assertEquals(status, pedido.getStatus());
        assertEquals(tempoEspera, pedido.getTempoEspera());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
    }

    @Test
    void construtorComIdECampos_CorretamenteInicializado() {
        // Arrange
        Long id = 1L;
        List<ProdutoVO> produtos = new ArrayList<>();
        produtos.add(new ProdutoVO("1", "2"));
        produtos.add(new ProdutoVO("2", "1"));
        String cliente = "123456";
        Double valorTotal = 100.00;
        StatusPedidoEnum status = StatusPedidoEnum.RECEBIDO;
        String tempoEspera = "30";
        StatusPagamentoEnum statusPagamento = StatusPagamentoEnum.PENDENTE;

        // Act
        Pedido pedido = new Pedido(id, produtos, cliente, valorTotal, status, tempoEspera, statusPagamento);

        // Assert
        assertNotNull(pedido);
        assertEquals(id, pedido.getId());
        assertEquals(produtos, pedido.getProdutos());
        assertEquals(cliente, pedido.getCliente());
        assertEquals(valorTotal, pedido.getValorTotal());
        assertEquals(status, pedido.getStatus());
        assertEquals(tempoEspera, pedido.getTempoEspera());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
    }



    @Test
    void testSetStatusPagamento() {
        // Cria uma instância da sua classe
        Pagamentos pagamento = new Pagamentos("APROVADO");

        // Define um status de pagamento
        StatusPagamentoEnum statusPagamento = StatusPagamentoEnum.APROVADO;

        // Testa se o status de pagamento foi definido corretamente
        assertEquals(statusPagamento.getNome(), pagamento.status());
    }

}
