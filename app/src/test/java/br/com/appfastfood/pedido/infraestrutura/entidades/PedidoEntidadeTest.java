package br.com.appfastfood.pedido.infraestrutura.entidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class PedidoEntidadeTest {

    @Test
    public void testConstrutorComParametros_DeveRetornarObjetoCorreto() {
        // Arrange
        List<ProdEnt> produtos = Arrays.asList(new ProdEnt(), new ProdEnt());
        String clienteId = "clienteId";
        Double valorTotal = 100.0;
        String status = "status";
        String tempoEspera = "tempoEspera";
        String statusPagamento = "statusPagamento";

        // Act
        PedidoEntidade pedido = new PedidoEntidade(produtos, clienteId, valorTotal, status, tempoEspera, statusPagamento);

        // Assert
        assertEquals(produtos, pedido.getProdutos());
        assertEquals(clienteId, pedido.getClienteId());
        assertEquals(valorTotal, pedido.getValorTotal());
        assertEquals(status, pedido.getStatus());
        assertEquals(tempoEspera, pedido.getTempoEspera());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
    }

    @Test
    public void testConstrutorSemId_DeveRetornarObjetoCorreto() {
        // Arrange
        List<ProdEnt> produtos = Arrays.asList(new ProdEnt(), new ProdEnt());
        String clienteId = "clienteId";
        Double valorTotal = 100.0;
        String status = "status";
        String tempoEspera = "tempoEspera";
        String statusPagamento = "statusPagamento";

        // Act
        PedidoEntidade pedido = new PedidoEntidade(produtos, clienteId, valorTotal, status, tempoEspera, statusPagamento);

        // Assert
        assertEquals(produtos, pedido.getProdutos());
        assertEquals(clienteId, pedido.getClienteId());
        assertEquals(valorTotal, pedido.getValorTotal());
        assertEquals(status, pedido.getStatus());
        assertEquals(tempoEspera, pedido.getTempoEspera());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
    }
}