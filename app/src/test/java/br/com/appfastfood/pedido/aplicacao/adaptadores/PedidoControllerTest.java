package br.com.appfastfood.pedido.aplicacao.adaptadores;

import br.com.appfastfood.pedido.aplicacao.adaptadores.PedidoController;
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
public class PedidoControllerTest {

    @Mock
    private PedidoServico pedidoServico;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    public void testCriar() {
        // Mock do ID retornado pelo serviço
        String idMock = "123";

        // Mock do retorno do serviço de criação de pedido
        when(pedidoServico.criar()).thenReturn(idMock);

        // Chamada ao método do controlador
        ResponseEntity<PedidoRequisicao> responseEntity = pedidoController.criar();

        // Verificações
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(idMock, responseEntity.getBody().getIdPedido());
    }

    @Test
    public void testAtualizarStatus() {
        // Mock do pedido retornado pelo serviço
        Pedido pedidoMock = criarPedidoConfigurado();
        // Configurar o pedidoMock conforme necessário

        // Mock do retorno do serviço de atualização de status
        when(pedidoServico.atualizar(1L)).thenReturn(pedidoMock);

        // Chamada ao método do controlador
        ResponseEntity<Pedido> responseEntity = pedidoController.atualizarStatus(1L);

        // Verificações
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pedidoMock, responseEntity.getBody());
    }

    @Test
    public void testBuscarPedidoPorID() throws JsonProcessingException {
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
    public void testListarPedidos() throws JsonProcessingException {
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
    public void testPagar() throws JsonProcessingException {
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

    public static Pedido criarPedidoConfigurado() {
        ProdutoVO produtoVO = new ProdutoVO("1", "2");
        List<ProdutoVO> produtos = List.of(produtoVO);

        return new Pedido(1L, produtos, "123456", 20.0, StatusPedidoEnum.RECEBIDO, "30", StatusPagamentoEnum.PENDENTE);
    }
}


//package br.com.appfastfood.pedido.aplicacao.adaptadores;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import br.com.appfastfood.configuracoes.client.carrinho.Carrinho;
//import br.com.appfastfood.configuracoes.client.carrinho.CarrinhoClient;
//import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
//import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
//import br.com.appfastfood.pedido.dominio.modelos.Pedido;
//import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
//import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
//import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
//import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
//import br.com.appfastfood.pedido.usecase.adaptadores.PedidoServicoImpl;
//import br.com.appfastfood.produto.dominio.modelos.Produto;
//import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//class PedidoServicoImplTest {
//
//    private PedidoRepositorio pedidoRepositorioMock;
//    private RestTemplate restTemplateMock;
//    private PedidoServicoImpl pedidoServico;
//
//    private CarrinhoClient carrinhoClient;
//
//    @BeforeEach
//    public void setUp() {
//        pedidoRepositorioMock = mock(PedidoRepositorio.class);
//        restTemplateMock = mock(RestTemplate.class);
//        pedidoServico = new PedidoServicoImpl(pedidoRepositorioMock, carrinhoClient);
//        pedidoServico.setRestTemplate(restTemplateMock);
//    }
//
//    @Test
//    public void criarPedido_DeveRetornarIdDoPedido() {
//        PedidoRequisicao pedidoReq = criarPedidoRequisicaoExemplo();
//        when(pedidoRepositorioMock.criar(any(Pedido.class))).thenReturn("1");
//        when(restTemplateMock.getForObject(anyString(), eq(Produto.class))).thenReturn(criarProdutoExemplo());
//
//        String idPedido = pedidoServico.criar(criarCarrinhoExemplo());
//
//        assertNotNull(idPedido);
//        assertEquals("1,", idPedido);
//    }
//
//    @Test
//    public void criarPedidos_DeveRetornarIdsDosPedidos() {
//        // Arrange
//        List<Carrinho> carrinho = criarCarrinhoExemplo();
//        when(pedidoRepositorioMock.criar(any(Pedido.class))).thenReturn("1");
//        when(restTemplateMock.getForObject(anyString(), eq(Produto.class))).thenReturn(criarProdutoExemplo());
//
//        // Act
//        String idsCriados = pedidoServico.criar(carrinho);
//
//        // Assert
//        assertNotNull(idsCriados);
//        assertEquals("1,", idsCriados);
//    }
//    @Test
//    public void atualizarPedido_DeveRetornarPedidoAtualizado() {
//
//        Long idPedido = 1L;
//        Pedido pedidoMock = criarPedidoConfigurado();
//
//        when(pedidoRepositorioMock.buscarPedidoPorId(idPedido)).thenReturn(pedidoMock);
//        when(pedidoRepositorioMock.atualizar(any(Pedido.class))).thenReturn(pedidoMock);
//
//        Pedido pedidoAtualizado = pedidoServico.atualizar(idPedido);
//
//        assertNotNull(pedidoAtualizado);
//        assertEquals(StatusPedidoEnum.RECEBIDO, pedidoAtualizado.getStatus());
//    }
//

//
//
//    private PedidoRequisicao criarPedidoRequisicaoExemplo() {
//        List<ProdutosReq> produtos = Arrays.asList(
//                ProdutosReq.builder()
//                        .idProduto("1")
//                        .quantidadeProduto("2")
//                        .build()
//        );
//
//        return PedidoRequisicao.builder()
//                .produtos(produtos)
//                .idCliente("123456")
//                .valorTotal(20.0)
//                .status("EM_PREPARACAO")
//                .tempoEspera("30")
//                .idPedido("1")
//                .statusPagamento("PENDENTE")
//                .build();
//    }
//
//    private Produto criarProdutoExemplo() {
//        return new Produto(
//                1L,
//                "X-Salada",
//                10.0,
//                "http://imagem.com/xsalada.jpg",
//                CategoriaEnum.lanche,
//                "Delicioso"
//        );
//    }
//
//    private List<Carrinho> criarCarrinhoExemplo() {
//        Carrinho.Produto produto = new Carrinho.Produto("1", 2);
//        List<Carrinho.Produto> produtos = List.of(produto);
//
//        return Collections.singletonList(new Carrinho(
//                1L,
//                "EM_PRODUCAO",
//                produtos,
//                "123456",
//                20.0
//        ));
//    }
//}
