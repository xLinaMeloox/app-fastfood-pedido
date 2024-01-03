package br.com.appfastfood.pedido.aplicacao.adaptadores;

import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.pedido.aplicacao.adaptadores.resposta.PedidoResposta;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Tudo sobre pedidos")
public class PedidoController {
        private PedidoServico pedidoServico;

        public PedidoController(PedidoServico pedidoServico) {
                this.pedidoServico = pedidoServico;
        }

        @PostMapping
        @Operation(summary = "Cadastrar Pedido", description = "Funcionalidade de criar um pedido")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "pedido cadastrado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoResposta.class)) }),
                        @ApiResponse(responseCode = "400", description = "", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RequisicaoExcecao.class))) })
        public ResponseEntity<?> criar(@RequestBody PedidoRequisicao pedidoRequisicao) {

                String id = this.pedidoServico.criar(pedidoRequisicao, "RECEBIDO", "1:00");
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(PedidoRequisicao.builder().idPedido(id).build());

        }

        @PutMapping("/{id}")
        @Operation(summary = "Atualizar status do pedido", description = "Funcionalidade de atualizar o status do pedido passando o parametro 'id' do pedido")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = {
                                        @Content() }),
                        @ApiResponse(responseCode = "400", description = "", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RequisicaoExcecao.class))) })
        public ResponseEntity<?> atualizarStatus(@PathVariable("id") Long id) {

                Pedido pedidoRetorno = this.pedidoServico.atualizar(id);

                PedidoRequisicao pedidoResposta = PedidoRequisicao
                                .builder()
                                .produtos(pedidoRetorno.getProdutos().stream()
                                                .map(produto -> ProdutosReq.builder()
                                                                .idProduto(produto.getIdProduto())
                                                                .quantidadeProduto(
                                                                                produto.getQuantidadeProduto())
                                                                .build())
                                                .collect(Collectors.toList()))
                                .idCliente(pedidoRetorno.getCliente())
                                .tempoEspera(pedidoRetorno.getTempoEspera())
                                .valorTotal(pedidoRetorno.getValorTotal())
                                .status(StatusPedidoEnum.retornaNomeEnum(pedidoRetorno.getStatus()))
                                .idPedido(pedidoRetorno.getId().toString()).build();

                return ResponseEntity.status(HttpStatus.OK).body(pedidoRetorno);
        }

        @GetMapping("/{id}")
        @Operation(summary = "Buscar pedidos por id", description = "Funcionalidade que retorna o pedido passando o parametro id do pedido")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Pedido filtrado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = List.class, subTypes = {
                                                        PedidoRequisicao.class })) }),
                        @ApiResponse(responseCode = "400", description = "", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RequisicaoExcecao.class))) })
        public ResponseEntity buscarPedidoPorID(@PathVariable(value = "id") Long id) throws JsonProcessingException {

                Pedido pedidoRetorno = this.pedidoServico.buscarPedidoPorId(id);

                PedidoRequisicao pedidoResposta = PedidoRequisicao
                                .builder()
                                .produtos(pedidoRetorno.getProdutos().stream()
                                                .map(produto -> ProdutosReq.builder()
                                                                .idProduto(produto.getIdProduto())
                                                                .quantidadeProduto(
                                                                                produto.getQuantidadeProduto())
                                                                .categoria(produto.getCategoria())
                                                                .preco(produto.getPreco())
                                                                .nome(produto.getNome())
                                                                .uriImagem(produto.getUriImagem())
                                                                .build())
                                                .collect(Collectors.toList()))
                                .idCliente(pedidoRetorno.getCliente())
                                .tempoEspera(pedidoRetorno.getTempoEspera())
                                .valorTotal(pedidoRetorno.getValorTotal())
                                .status(StatusPedidoEnum.retornaNomeEnum(pedidoRetorno.getStatus()))
                                .idPedido(pedidoRetorno.getId().toString()).build();

                return ResponseEntity.status(HttpStatus.OK).body(pedidoResposta);

        }

        @GetMapping
        @Operation(summary = "Buscar todos pedidos", description = "Funcionalidade que retorna todos pedidos")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Pedidos filtrado com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = List.class, subTypes = {
                                                        PedidoRequisicao.class })) }),
                        @ApiResponse(responseCode = "400", description = "", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RequisicaoExcecao.class))) })
        public ResponseEntity<Object> listarPedidos() throws JsonProcessingException {

                List<Pedido> pedidos = this.pedidoServico.listarTodosPedidos();
                List<PedidoRequisicao> pedidoRespostas = new ArrayList<>();
                pedidos.forEach(pedido -> {
                        PedidoRequisicao pedidoResposta = PedidoRequisicao
                                        .builder()
                                        .produtos(pedido.getProdutos().stream()
                                        .map(produto -> ProdutosReq.builder()
                                        .idProduto(produto.getIdProduto())
                                        .quantidadeProduto(produto
                                        .getQuantidadeProduto())
                                        .categoria(produto.getCategoria())
                                        .preco(produto.getPreco())
                                        .nome(produto.getNome())
                                        .uriImagem(produto.getUriImagem())
                                        .build())
                                        .collect(Collectors.toList()))
                                        .idCliente(pedido.getCliente())
                                        .tempoEspera(pedido.getTempoEspera())
                                        .valorTotal(pedido.getValorTotal())
                                        .status(StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()))
                                        .idPedido(pedido.getId().toString())
                                        .statusPagamento(StatusPagamentoEnum
                                                        .retornaNomeStatusPagamentoEnum(pedido.getStatusPagamento()))
                                        .build();
                        pedidoRespostas.add(pedidoResposta);
                });

                return ResponseEntity.status(HttpStatus.OK).body(pedidoRespostas);
        }

        @GetMapping("/{id}/status_pagamento")
        @Operation(summary = "Buscar status pagamento", description = "Funcionalidade o status do pagamento do pedido por id.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Pedidos filtrados com sucesso", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = List.class, subTypes = {
                                                        PedidoRequisicao.class })) }),
                        @ApiResponse(responseCode = "400", description = "", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RequisicaoExcecao.class))) })
        public ResponseEntity buscarStatusPagamento(@PathVariable(value = "id") Long id)
                        throws JsonProcessingException {

                Pedido pedido = this.pedidoServico.buscarPedidoPorId(id);

                PedidoRequisicao pedidoResposta = PedidoRequisicao
                                .builder()
                                .idPedido(pedido.getId().toString())
                                .statusPagamento(StatusPagamentoEnum
                                                .retornaNomeStatusPagamentoEnum(pedido.getStatusPagamento()))
                                .build();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pedidoResposta);

        }

}
