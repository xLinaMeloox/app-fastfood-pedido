package br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao;

import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtualizarPedidoRequisicaoTest {

    @Test
    void getId() {
        Long id = 1L;
        assertEquals(1L, id);
    }

    @Test
    void getStatus() {
        StatusPedidoEnum status = StatusPedidoEnum.RECEBIDO;
        assertEquals(StatusPedidoEnum.RECEBIDO, status);
    }
}