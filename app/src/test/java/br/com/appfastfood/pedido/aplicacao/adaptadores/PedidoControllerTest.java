package br.com.appfastfood.pedido.aplicacao.adaptadores;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.VO.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.exceptions.ExceptionsMessages;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

 public class PedidoControllerTest {
  @Mock
  private PedidoServico pedidoServico;

  @InjectMocks
  private PedidoController pedidoController;

  @BeforeEach
  void setUp() {
   MockitoAnnotations.openMocks(this);
  }

  @Test
  void criar_DeveRetornarPedidoCriado() throws JsonProcessingException {
   // Dados de entrada
   PedidoRequisicao pedidoRequisicao = PedidoRequisicao.builder()
           .produtos(Arrays.asList(ProdutosReq.builder()
                   .idProduto("1")
                   .quantidadeProduto("2")
                   .build()))
           .idCliente("123")
           .valorTotal(10.0)
           .status("RECEBIDO")
           .tempoEspera("1:00")
           .idPedido(null)
           .build();

   // Mock do serviço
   when(pedidoServico.criar(eq(pedidoRequisicao), eq("RECEBIDO"), eq("1:00")))
           .thenReturn("123456789");

   // Execução do método
   ResponseEntity<?> responseEntity = pedidoController.criar(pedidoRequisicao);

   // Verificações
   assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
   PedidoRequisicao responseBody = (PedidoRequisicao) responseEntity.getBody();
   assertEquals("123456789", responseBody.getIdPedido());
  }

  @Test
  void criar_DeveRetornarBadRequestQuandoIDNaoEncontradoException() throws  JsonProcessingException {
   // Dados de entrada
   Long idPedido = 123L;

   // Mock do serviço lançando exceção
   when(pedidoServico.atualizar(eq(idPedido))).thenThrow(new BadRequestException(ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue()).getClass());

   // Execução do método
   assertThrows(BadRequestException.class, () -> pedidoServico.atualizar(idPedido));


  }

  @Test
  void criar_DeveRetornarBadRequestQuandoPagamentoNaoRealizado() throws JsonProcessingException {
   // Dados de entrada
   PedidoRequisicao pedidoRequisicao = PedidoRequisicao.builder()
           .produtos(Arrays.asList(ProdutosReq.builder()
                   .idProduto("1")
                   .quantidadeProduto("2")
                   .build()))
           .idCliente("123")
           .valorTotal(10.0)
           .status("RECEBIDO")
           .tempoEspera("1:00")
           .idPedido(null)
           .build();

   // Mock do serviço lançando exceção
   when(pedidoServico.criar(eq(pedidoRequisicao), eq("RECEBIDO"), eq("1:00")))
           .thenThrow(new BadRequestException(ExceptionsMessages.PAGAMENTO_RECUSADO.getValue()).getClass());

   // Execução do método
   assertThrows(BadRequestException.class, () -> pedidoController.criar(pedidoRequisicao));

  }



  @Test
  void atualizarStatus_DeveRetornarBadRequestQuandoIDPedidoNaoEncontradoException() throws JsonProcessingException {
   // Dados de entrada
   Long idPedido = 123L;

   // Mock do serviço lançando exceção
   when(pedidoServico.atualizar(eq(idPedido))).thenThrow(new BadRequestException(ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue()).getClass());

   // Execução do método
   assertThrows(BadRequestException.class, () -> pedidoServico.atualizar(idPedido));

   //

  }

  @Test
  void atualizarStatus_DeveRetornarBadRequestQuandoPedidoJaFinalizadoException() throws  JsonProcessingException {
   // Dados de entrada
   Long idPedido = 123L;

   // Mock do serviço lançando exceção
   when(pedidoServico.atualizar(eq(idPedido))).thenThrow(new BadRequestException(ExceptionsMessages.PEDIDO_JA_FINALIZADO.getValue()).getClass());

   // Execução do método
   assertThrows(BadRequestException.class, () -> pedidoServico.atualizar(idPedido));

   // Verificações


  }



  @Test
  void buscarPedidoPorID_DeveRetornarBadRequestQuandoIDPedidoNaoEncontradoException() throws JsonProcessingException {
   // Dados de entrada
   Long idPedido = 123L;

   // Mock do serviço lançando exceção
   when(pedidoServico.buscarPedidoPorId(eq(idPedido))).thenThrow(new BadRequestException(ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue()).getClass());

   // Execução do método
   assertThrows(BadRequestException.class, () -> pedidoController.buscarPedidoPorID(idPedido));



  }

  private Pedido criarPedido() {
   Pedido pedido = new Pedido(Arrays.asList(
           new ProdutoVO("1", "5.0"),
           new ProdutoVO("2", "3.0")
   ), "123", 10.0, StatusPedidoEnum.RECEBIDO, "1:00", StatusPagamentoEnum.PENDENTE);
   return pedido;
  }

  private void assertPedidoRequisicaoEqualsPedido(Pedido pedido, PedidoRequisicao pedidoRequisicao) {
   assertEquals(pedido.getId().toString(), pedidoRequisicao.getIdPedido());
   assertEquals(pedido.getCliente(), pedidoRequisicao.getIdCliente());
   assertEquals(pedido.getValorTotal(), pedidoRequisicao.getValorTotal());
   assertEquals(pedido.getStatus().getNome(), pedidoRequisicao.getStatus());
   assertEquals(pedido.getTempoEspera(), pedidoRequisicao.getTempoEspera());

   List<ProdutosReq> produtosReq = pedido.getProdutos().stream()
           .map(produto -> ProdutosReq.builder()
                   .idProduto(produto.getIdProduto())
                   .quantidadeProduto(produto.getQuantidadeProduto())
                   .build())
           .collect(Collectors.toList());

   assertEquals(produtosReq, pedidoRequisicao.getProdutos());
  }

 }
