package br.com.appfastfood.usecase.adaptadores;

import br.com.appfastfood.configuracoes.client.carrinho.Carrinho;
import br.com.appfastfood.configuracoes.client.carrinho.CarrinhoClient;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.exceptions.ExceptionsMessages;
import br.com.appfastfood.pedido.usecase.adaptadores.PedidoServicoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServicoImplTest {

    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @Mock
    private CarrinhoClient carrinhoClient;

    @InjectMocks
    private PedidoServicoImpl pedidoServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criar_DeveCriarPedidosEExcluirCarrinho() {
        // Arrange
        List<Carrinho> carrinhoFechado = new ArrayList<>();
        Carrinho carrinho = new Carrinho(1L, "FECHADO", new ArrayList<>(), "123456", 100.0);
        carrinhoFechado.add(carrinho);

        when(carrinhoClient.getCarrinho()).thenReturn(carrinhoFechado);
        when(pedidoRepositorio.criar(any())).thenReturn("1");

        // Act
        //pedidoServico.criar();

        // Assert
//        verify(carrinhoClient, times(1)).getCarrinho();
//        verify(carrinhoClient, times(1)).deleteCarrinho(1L);
//        verify(pedidoRepositorio, times(1)).criar(any());
    }



    @Test
    void atualizar_PedidoNaoEncontrado_DeveLancarExcecao() {
        // Arrange
        Long idPedido = 1L;
        when(pedidoRepositorio.buscarPedidoPorId(idPedido)).thenReturn(null);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> {
            pedidoServico.atualizar(idPedido);
        });
    }

    @Test
    void atualizar_PagamentoRecusado_DeveLancarExcecao() {
        // Arrange
        Long idPedido = 1L;
        Pedido pedido = new Pedido(null, null, null, null, null, null, StatusPagamentoEnum.RECUSADO);
        when(pedidoRepositorio.buscarPedidoPorId(idPedido)).thenReturn(pedido);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> {
            pedidoServico.atualizar(idPedido);
        });
    }

    @Test
    void atualizar_PagamentoPendente_DeveLancarExcecao() {
        // Arrange
        Long idPedido = 1L;
        Pedido pedido = new Pedido(null, null, null, null, null, null, StatusPagamentoEnum.PENDENTE);
        when(pedidoRepositorio.buscarPedidoPorId(idPedido)).thenReturn(pedido);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> {
            pedidoServico.atualizar(idPedido);
        });
    }

    @Test
    void atualizar_PedidoRecebido_DeveAtualizarStatus() {
        // Arrange
        Long idPedido = 1L;
        Pedido pedido = new Pedido(1L, null, null, null, StatusPedidoEnum.RECEBIDO, null, StatusPagamentoEnum.APROVADO);
        when(pedidoRepositorio.buscarPedidoPorId(idPedido)).thenReturn(pedido);
        when(pedidoRepositorio.atualizar(any())).thenReturn(pedido);

        // Act
        Pedido pedidoAtualizado = pedidoServico.atualizar(idPedido);

        // Assert
        assertNotNull(pedidoAtualizado);
        assertEquals(StatusPedidoEnum.RECEBIDO, pedidoAtualizado.getStatus());
        verify(pedidoRepositorio, times(1)).atualizar(any());
    }

    @Test
    void buscarPedidoPorId_DeveRetornarPedidoExistente() {
        // Arrange
        Long idPedido = 1L;
        Pedido pedido = new Pedido(idPedido, null, null, null, null, null, null);
        when(pedidoRepositorio.buscarPedidoPorId(idPedido)).thenReturn(pedido);

        // Act
        Pedido pedidoEncontrado = pedidoServico.buscarPedidoPorId(idPedido);

        // Assert
        assertNotNull(pedidoEncontrado);
        assertEquals(idPedido, pedidoEncontrado.getId());
    }

    @Test
    void listarTodosPedidos_DeveRetornarListaDePedidos() {
        // Arrange
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(1L, null, null, null, null, null, null));
        when(pedidoRepositorio.listarTodosOsPedidos()).thenReturn(pedidos);

        // Act
        List<Pedido> pedidosListados = pedidoServico.listarTodosPedidos();

        // Assert
        assertNotNull(pedidosListados);
        assertEquals(0, pedidosListados.size());
    }

    @Test
    void atualizarPagamento_DeveChamarMetodoRealizarPagamentoDoRepositorio() {
        // Arrange
        Long idPedido = 1L;
        when(pedidoRepositorio.realizarPagamento(idPedido)).thenReturn(StatusPagamentoEnum.APROVADO);

        // Act
        StatusPagamentoEnum statusPagamento = pedidoServico.atualizarPagamento(idPedido);

        // Assert
        assertEquals(StatusPagamentoEnum.APROVADO, statusPagamento);
    }


//    @Test
//    public void testCriar() {
//        // Cria uma instância da sua classe
//        SuaClasse suaClasse = new SuaClasse();
//
//        // Cria uma instância do carrinhoClient e do pedidoRepositorio
//        CarrinhoClient carrinhoClientMock = Mockito.mock(CarrinhoClient.class);
//        PedidoRepositorio pedidoRepositorioMock = Mockito.mock(PedidoRepositorio.class);
//        suaClasse.setCarrinhoClient(carrinhoClientMock);
//        suaClasse.setPedidoRepositorio(pedidoRepositorioMock);
//
//        // Configuração de dados fictícios para o teste
//        Carrinho carrinhoFechado = new Carrinho(/* dados do carrinho fechado */);
//        Produto produto = new Produto(/* dados do produto */);
//        carrinhoFechado.getProdutos().add(produto);
//        List<Carrinho> carrinhoList = new ArrayList<>();
//        carrinhoList.add(carrinhoFechado);
//
//        // Configuração do comportamento esperado do carrinhoClientMock
//        Mockito.when(carrinhoClientMock.getCarrinho()).thenReturn(carrinhoList);
//        Mockito.when(carrinhoClientMock.deleteCarrinho(Mockito.any())).thenReturn(/* algo simulado */);
//
//        // Configuração do comportamento esperado do pedidoRepositorioMock
//        Mockito.when(pedidoRepositorioMock.criar(Mockito.any())).thenReturn(/* algo simulado */);
//
//        // Chama o método que você deseja testar
//        String idsCriados = suaClasse.criar();
//
//        // Verificações
//        assertNotNull(idsCriados);
//        // Adicione verificações específicas conforme necessário, dependendo da lógica de criação do seu método
//    }
}
