package br.com.appfastfood.pedido.aplicacao.adaptadores;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.appfastfood.configuracoes.client.carrinho.Carrinho;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.VO.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.usecase.adaptadores.PedidoServicoImpl;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PedidoServicoImplTest {

    private PedidoRepositorio pedidoRepositorioMock;
    private RestTemplate restTemplateMock;
    private PedidoServicoImpl pedidoServico;

    @BeforeEach
    public void setUp() {
        pedidoRepositorioMock = mock(PedidoRepositorio.class);
        restTemplateMock = mock(RestTemplate.class);
        pedidoServico = new PedidoServicoImpl(pedidoRepositorioMock);
        pedidoServico.setRestTemplate(restTemplateMock);
    }

    @Test
    public void criarPedido_DeveRetornarIdDoPedido() {
        PedidoRequisicao pedidoReq = criarPedidoRequisicaoExemplo();
        when(pedidoRepositorioMock.criar(any(Pedido.class))).thenReturn("1");
        when(restTemplateMock.getForObject(anyString(), eq(Produto.class))).thenReturn(criarProdutoExemplo());

        String idPedido = pedidoServico.criar(criarCarrinhoExemplo());

        assertNotNull(idPedido);
        assertEquals("1,", idPedido);
    }

    @Test
    public void criarPedidos_DeveRetornarIdsDosPedidos() {
        // Arrange
        List<Carrinho> carrinho = criarCarrinhoExemplo();
        when(pedidoRepositorioMock.criar(any(Pedido.class))).thenReturn("1");
        when(restTemplateMock.getForObject(anyString(), eq(Produto.class))).thenReturn(criarProdutoExemplo());

        // Act
        String idsCriados = pedidoServico.criar(carrinho);

        // Assert
        assertNotNull(idsCriados);
        assertEquals("1,", idsCriados);
    }
    @Test
    public void atualizarPedido_DeveRetornarPedidoAtualizado() {

        Long idPedido = 1L;
        Pedido pedidoMock = criarPedidoConfigurado();

        when(pedidoRepositorioMock.buscarPedidoPorId(idPedido)).thenReturn(pedidoMock);
        when(pedidoRepositorioMock.atualizar(any(Pedido.class))).thenReturn(pedidoMock);

        Pedido pedidoAtualizado = pedidoServico.atualizar(idPedido);

        assertNotNull(pedidoAtualizado);
        assertEquals(StatusPedidoEnum.RECEBIDO, pedidoAtualizado.getStatus());
    }

    public static Pedido criarPedidoConfigurado() {
        ProdutoVO produtoVO = new ProdutoVO("1", "2");
        List<ProdutoVO> produtos = List.of(produtoVO);

        return new Pedido(1L, produtos, "123456", 20.0, StatusPedidoEnum.RECEBIDO, "30", StatusPagamentoEnum.PENDENTE);
    }


    private PedidoRequisicao criarPedidoRequisicaoExemplo() {
        List<ProdutosReq> produtos = Arrays.asList(
                ProdutosReq.builder()
                        .idProduto("1")
                        .quantidadeProduto("2")
                        .build()
        );

        return PedidoRequisicao.builder()
                .produtos(produtos)
                .idCliente("123456")
                .valorTotal(20.0)
                .status("EM_PREPARACAO")
                .tempoEspera("30")
                .idPedido("1")
                .statusPagamento("PENDENTE")
                .build();
    }

    private Produto criarProdutoExemplo() {
        return new Produto(
                1L,
                "X-Salada",
                10.0,
                "http://imagem.com/xsalada.jpg",
                CategoriaEnum.lanche,
                "Delicioso"
        );
    }

    private List<Carrinho> criarCarrinhoExemplo() {
        Carrinho.Produto produto = new Carrinho.Produto("1", 2);
        List<Carrinho.Produto> produtos = List.of(produto);

        return Collections.singletonList(new Carrinho(
                1L,
                "EM_ANDAMENTO",
                produtos,
                "123456",
                20.0
        ));
    }
}
