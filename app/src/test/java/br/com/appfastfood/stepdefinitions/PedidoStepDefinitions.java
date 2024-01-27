package br.com.appfastfood.stepdefinitions;

import br.com.appfastfood.AppFastfoodApplication;
import br.com.appfastfood.usecase.CarrinhoService;
import br.com.appfastfood.usecase.PagamentoService;
import br.com.appfastfood.usecase.PedidoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= AppFastfoodApplication.class)
@CucumberContextConfiguration
public class PedidoStepDefinitions {

    CarrinhoService carrinhoService = new CarrinhoService();
    PedidoService pedidoService = new PedidoService();
    PagamentoService pagamentoService = new PagamentoService();
    String resposta;

    @Dado("que a api de carrinho retornou um carrinho fechado")
    public void apiCarrinhoRetornouCarrinhoFechado() {
        assertTrue(carrinhoService.isCarrinhoFechado());
    }

    @Então("o pedido será criado")
    public void pedidoSeraCriado() {
        resposta = pedidoService.criarPedido(carrinhoService.obterProdutos());
        assertNotNull(resposta);
        assertTrue(resposta.contains("Pedido criado com sucesso"));
    }

    @Dado("que o status do pagamento esteja aprovado")
    public void statusPagamentoAprovado() {
        pagamentoService.simularStatusAprovado();
    }

    @Então("status de pagamento do pedido será atualizado para aprovado")
    public void atualizarStatusPagamentoAprovado() {
        resposta = pedidoService.atualizarStatusPagamento(pagamentoService.simularStatusAprovado());
        assertEquals("Aprovado", resposta);
    }

    @Quando("o status do pagamento for reprovado")
    public void statusPagamentoReprovado() {
        pagamentoService.simularStatusReprovado();
    }

    @Então("status de pagamento do pedido será atualizado para reprovado")
    public void atualizarStatusPagamentoReprovado() {
        resposta = pedidoService.atualizarStatusPagamento(pagamentoService.simularStatusReprovado());
        assertEquals("Reprovado", resposta);
    }

    @Dado("que status de pagamento esteja aprovado")
    public void statusPagamentoAprovadoParaPedido() {
        resposta = pedidoService.atualizarStatusPagamento(Boolean.TRUE);
        assertEquals("Em Produção", resposta);
    }

    @Então("o status do pedido será atualizado em produção")
    public void atualizarStatusPedidoProducao() {
        resposta = pedidoService.atualizarStatusProducao();
        assertEquals("Em Produção", resposta);
    }

    @Quando("o status do pagamento for reprovado")
    public void statusPagamentoReprovadoParaPedido() {
        resposta = pedidoService.atualizarStatusPagamento(Boolean.FALSE);
        assertEquals("Cancelado", resposta);
    }

    @Então("o status do pedido será atualizado para cancelado")
    public void atualizarStatusPedidoCancelado() {
        resposta = pedidoService.atualizarStatusCancelado();
        assertEquals("Cancelado", resposta);
    }

    @Dado("que status do pedido estiver em preparação")
    public void statusPedidoPreparacao() {
        resposta = pedidoService.atualizarStatusProducao();
        assertEquals("Em Produção", resposta);
    }

    @Então("o status do pedido será atualizado para pronto")
    public void atualizarStatusPedidoPronto() {
        resposta = pedidoService.atualizarStatusPronto();
        assertEquals("Pronto", resposta);
    }

    @Dado("que status do pedido estiver pronto")
    public void statusPedidoPronto() {
        resposta = pedidoService.atualizarStatusPronto();
        assertEquals("Pronto", resposta);
    }

    @Então("o status do pedido será atualizado para finalizado")
    public void atualizarStatusPedidoFinalizado() {
        resposta = pedidoService.atualizarStatusFinalizado();
        assertEquals("Finalizado", resposta);
    }
}
