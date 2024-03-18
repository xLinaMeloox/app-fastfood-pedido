package br.com.appfastfood.pedido.aplicacao.adaptadores;

import br.com.appfastfood.configuracoes.client.carrinho.Carrinho;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidoServico pedidoServico;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
     void testCriar() {
        // Mock do ID retornado pelo serviço
        String idMock = "123";

        // Mock do retorno do serviço de criação de pedido
        //when(pedidoServico.criar("")).thenReturn(idMock);

        // Chamada ao método do controlador
        //ResponseEntity<PedidoRequisicao> responseEntity = pedidoController.criar();

        // Verificações
        //assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        //assertEquals("123", idMock);
    }

    // @Test
    //  void testAtualizarStatus() {
    //     // Mock do pedido retornado pelo serviço
    //     Pedido pedidoMock = criarPedidoConfigurado();
    //     // Configurar o pedidoMock conforme necessário

    //     // Mock do retorno do serviço de atualização de status
    //     when(pedidoServico.atualizar(1L, false)).thenReturn(pedidoMock);

    //     // Chamada ao método do controlador
    //     ResponseEntity<Pedido> responseEntity = pedidoController.atualizarStatus(1L);

    //     // Verificações
    //     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    //     assertEquals(pedidoMock, responseEntity.getBody());
    // }

    @Test
     void testBuscarPedidoPorID() throws JsonProcessingException {
        // Mock do pedido retornado pelo serviço
        Pedido pedidoMock = criarPedidoConfigurado();
        // Configurar o pedidoMock conforme necessário

        // Mock do retorno do serviço de pedido por ID
        when(pedidoServico.buscarPedidoPorId(1L)).thenReturn(pedidoMock);

        // Chamada ao método do controlador
        ResponseEntity<PedidoRequisicao> responseEntity = pedidoController.buscarPedidoPorID(1L);

        // Verificações
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Adicione mais verificações conforme necessário
    }

    @Test
     void testListarPedidos() throws JsonProcessingException {
        // Mock da lista de pedidos retornada pelo serviço
        List<Pedido> pedidosMock = Collections.singletonList(criarPedidoConfigurado());
        // Configurar os pedidosMock conforme necessário

        // Mock do retorno do serviço de listar pedidos
        when(pedidoServico.listarTodosPedidos()).thenReturn(pedidosMock);

        // Chamada ao método do controlador
        ResponseEntity<List<PedidoRequisicao>> responseEntity = pedidoController.listarPedidos();

        // Verificações
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Adicione mais verificações conforme necessário
    }

    @Test
     void testPagar() throws JsonProcessingException {
        // Mock do status de pagamento retornado pelo serviço
        StatusPagamentoEnum statusPagamentoMock;
        statusPagamentoMock = StatusPagamentoEnum.APROVADO;

        // Mock do retorno do serviço de pagamento
        when(pedidoServico.atualizarPagamento(1L)).thenReturn(statusPagamentoMock);

        // Chamada ao método do controlador
        ResponseEntity<StatusPagamentoEnum> responseEntity = pedidoController.pagar(1L);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(statusPagamentoMock, responseEntity.getBody());
    }

     static Pedido criarPedidoConfigurado() {
        ProdutoVO produtoVO = new ProdutoVO("1", "2");
        List<ProdutoVO> produtos = List.of(produtoVO);

        return new Pedido(1L, produtos, "123456", 20.0, StatusPedidoEnum.RECEBIDO, "30", StatusPagamentoEnum.PENDENTE);
    }
}