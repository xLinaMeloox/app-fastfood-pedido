package br.com.appfastfood.pedido.exceptions;

import br.com.appfastfood.pedido.exceptions.ExceptionsMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionsMessagesTest {

    @Test
    void testPedidoNaoEncontrado() {
        assertEquals("Pedido não encontrado!", ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue());
    }

    @Test
    void testPagamentoNaoFoiAprovado() {
        assertEquals("Pagamento não foi aprovado.", ExceptionsMessages.PAGAMENTO_NAO_FOI_APROVADO.getValue());
    }

    @Test
    void testPedidoJaFinalizado() {
        assertEquals("Pedido já está finalizado! Não é possível alterar seu status.",
                ExceptionsMessages.PEDIDO_JA_FINALIZADO.getValue());
    }

    @Test
    void testPagamentoPendente() {
        assertEquals("Pagamento do pedido está pendente.", ExceptionsMessages.PAGAMENTO_PENDENTE.getValue());
    }

    @Test
    void testStatusPagamentoNaoEncontrado() {
        assertEquals("O status de pagamento informado não existe.",
                ExceptionsMessages.STATUS_PAGAMENTO_NAO_ENCONTRADO.getValue());
    }

    @Test
    void testPagamentoRecusado() {
        assertEquals("Pagamento recusado!", ExceptionsMessages.PAGAMENTO_RECUSADO.getValue());
    }

    @Test
    void testStatusPedidoNaoPermitido() {
        assertEquals("Status não permitido!", ExceptionsMessages.STATUS_PEDIDO_NAO_PERMITIDO.getValue());
    }
}
