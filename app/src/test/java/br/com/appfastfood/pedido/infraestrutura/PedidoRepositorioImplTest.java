package br.com.appfastfood.pedido.infraestrutura;

import br.com.appfastfood.configuracoes.client.pagamento.PagamentoClient;
import br.com.appfastfood.configuracoes.client.pagamento.PagamentoRequisicao;
import br.com.appfastfood.configuracoes.client.pagamento.Pagamentos;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.infraestrutura.entidades.PedidoEntidade;
import br.com.appfastfood.pedido.infraestrutura.entidades.ProdEnt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PedidoRepositorioImplTest {

    @Mock
    private SpringDataPedidoRepository springDataPedidoRepository;

    @Mock
    private PagamentoClient pagamentoClient;

    @InjectMocks
    private PedidoRepositorioImpl pedidoRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void atualizar_DeveRetornarPedidoAtualizado() {
        // Arrange
        Pedido pedido = mockPedido();

        // Act
        Pedido pedidoAtualizado = pedidoRepositorio.atualizar(pedido);

        // Assert
        assertEquals(pedido, pedidoAtualizado);
        verify(springDataPedidoRepository, times(1)).save(any());
    }

    @Test
    void listarTodosOsPedidos_DeveRetornarListaDePedidos() {
        // Arrange
        List<PedidoEntidade> pedidoEntidades = new ArrayList<>();
        pedidoEntidades.add(mockPedidoEntidade());
        when(springDataPedidoRepository.findNotInFinalzado()).thenReturn(pedidoEntidades);


        PedidoRepositorio pedidoRepositorioMock = mock(PedidoRepositorio.class);

        List<Pedido> pedidosMock = new ArrayList<>();
        when(pedidoRepositorioMock.listarTodosOsPedidos()).thenReturn(pedidosMock);

        List<Pedido> pedidos = pedidoRepositorioMock.listarTodosOsPedidos();

        // Assert
        assertNotNull(pedidos);
        assertTrue(pedidos.isEmpty());
        assertEquals(0, pedidos.size());
    }

    @Test
    void buscarPedidoPorId_QuandoPedidoExiste_DeveRetornarPedido() {
        // Arrange
        Long idPedido = 1L;
        PedidoEntidade pedidoEntidade = mockPedidoEntidade();
        pedidoEntidade.setId(idPedido);
        when(springDataPedidoRepository.findById(idPedido)).thenReturn(Optional.of(pedidoEntidade));

        // Act
        Pedido pedido = mockPedido();

        // Assert
        assertNotNull(pedido);
        assertEquals(idPedido, pedido.getId());
    }

    @Test
    void buscarPedidoPorId_QuandoPedidoNaoExiste_DeveLancarExcecao() {
        // Arrange
        Long idPedido = 1L;
        when(springDataPedidoRepository.findById(idPedido)).thenReturn(Optional.empty());

        // Act && Assert
        assertThrows(BadRequestException.class, () -> {
            pedidoRepositorio.buscarPedidoPorId(idPedido);
        });
    }

    @Test
    void realizarPagamento_DeveRetornarStatusPagamento() {
        // Arrange
        Long idPedido = 1L;
        PedidoEntidade pedidoEntidade = mockPedidoEntidade();
        pedidoEntidade.setId(idPedido);
        when(springDataPedidoRepository.findById(idPedido)).thenReturn(Optional.of(pedidoEntidade));

        PagamentoRequisicao requisicao = PagamentoRequisicao.builder()
                .meioPagamento("Cartao")
                .idMeioPagamento("1234567891011121")
                .valor(100.0) // Valor do pedido
                .build();
        Pagamentos pagamentoRetorno = mockPagamentos("APROVADO");
        when(pagamentoClient.fazerPagamento(requisicao)).thenReturn(pagamentoRetorno);

        // Act
        StatusPagamentoEnum statusPagamento = StatusPagamentoEnum.APROVADO;

        // Assert
        assertEquals(StatusPagamentoEnum.APROVADO, statusPagamento);
    }

    @Test
    public void realizarPagamento_QuandoPagamentoAprovado_DeveRetornarStatusAprovado() {
        // Arrange
        Long idPedido = 1L;
        PedidoEntidade pedidoEntidade = mockPedidoEntidade();
        pedidoEntidade.setId(idPedido);
        when(springDataPedidoRepository.findById(idPedido)).thenReturn(Optional.of(pedidoEntidade));

        Pagamentos pagamentoRetorno = mockPagamentos("APROVADO");
        when(pagamentoClient.fazerPagamento(any())).thenReturn(pagamentoRetorno);

        // Act
        StatusPagamentoEnum statusPagamento = pedidoRepositorio.realizarPagamento(idPedido);

        // Assert
        assertEquals(StatusPagamentoEnum.APROVADO, statusPagamento);
    }

    @Test
    public void realizarPagamento_QuandoPagamentoRecusado_DeveRetornarStatusRecusado() {
        // Arrange
        Long idPedido = 1L;
        PedidoEntidade pedidoEntidade = mockPedidoEntidade();
        pedidoEntidade.setId(idPedido);
        when(springDataPedidoRepository.findById(idPedido)).thenReturn(Optional.of(pedidoEntidade));


        Pagamentos pagamentoRetorno = mockPagamentos("RECUSADO");
        when(pagamentoClient.fazerPagamento(any())).thenReturn(pagamentoRetorno);

        // Act
        StatusPagamentoEnum statusPagamento = pedidoRepositorio.realizarPagamento(idPedido);

        // Assert
        assertEquals(StatusPagamentoEnum.RECUSADO, statusPagamento);
    }

    @Test
    public void listarTodosOsPedidos_QuandoNaoHaPedidos_DeveLancarExcecao() {
        // Arrange
        when(springDataPedidoRepository.findNotInFinalzado()).thenReturn(Collections.emptyList());

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> {
            pedidoRepositorio.listarTodosOsPedidos();
        });
    }

    @Test
    void listarTodosOsPedidos_QuandoHaverPedidos_DeveRetornarListaDePedidos() {
        // Arrange
        List<PedidoEntidade> pedidoEntidades = new ArrayList<>();
        pedidoEntidades.add(mockPedidoEntidade());
        when(springDataPedidoRepository.findNotInFinalzado()).thenReturn(pedidoEntidades);

        // Act
        List<Pedido> pedidos = pedidoRepositorio.listarTodosOsPedidos();

        // Assert
        assertNotNull(pedidos);
        assertFalse(pedidos.isEmpty());
        assertEquals(1, pedidos.size());
    }



    private Pedido mockPedido() {
        List<ProdutoVO> produtos = new ArrayList<>();
        produtos.add(new ProdutoVO("1", "2"));
        return new Pedido(1L, produtos, "cliente", 100.0, StatusPedidoEnum.RECEBIDO, "30", StatusPagamentoEnum.PENDENTE);
    }

    public static PedidoEntidade mockPedidoEntidade() {
        List<ProdEnt> produtos = new ArrayList<>();
        return new PedidoEntidade(1L, produtos, "clienteId", 100.0, "EM_PREPARACAO", "tempoEspera", "APROVADO");
    }

    public static Pagamentos mockPagamentos(String status) {
        return new Pagamentos(status);
    }
}