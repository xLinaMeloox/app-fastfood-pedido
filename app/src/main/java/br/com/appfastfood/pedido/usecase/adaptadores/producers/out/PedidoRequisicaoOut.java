package br.com.appfastfood.pedido.usecase.adaptadores.producers.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoRequisicaoOut implements Serializable {

    @JsonProperty("produtos")
    private List<ProdutosRequisicaoOut> produtos;

    @JsonProperty("id_cliente")
    private String idCliente;

    @JsonProperty("valor_total")
    private Double valorTotal;

    @JsonProperty("status")
    private String status;

    @JsonProperty("tempo_espera")
    private String tempoEspera;

    @JsonProperty("id_pedido")
    private String idPedido;

    @JsonProperty("status_pagamento")
    private String statusPagamento;

    public PedidoRequisicaoOut(List<ProdutosRequisicaoOut> produtos,
                                    String idCliente,
                                    Double valorTotal,
                                    String status,
                                    String tempoEspera, String idPedido, String statusPagamento) {
        this.idCliente = idCliente;
        this.valorTotal = valorTotal;
        this.produtos = produtos;
        this.status = status;
        this.tempoEspera = tempoEspera;
        this.idPedido = idPedido;
        this.statusPagamento = statusPagamento;
    }


    public PedidoRequisicaoOut() {
    }
}
