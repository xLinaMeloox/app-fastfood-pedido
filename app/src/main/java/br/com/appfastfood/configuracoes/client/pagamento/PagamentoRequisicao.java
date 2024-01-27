package br.com.appfastfood.configuracoes.client.pagamento;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Builder()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagamentoRequisicao implements Serializable {

    @JsonProperty("meio_pagamento")
    private String meioPagamento;

    @JsonProperty("id_meio_pagamento")
    private String idMeioPagamento;

    @JsonProperty("valor")
    private Double valor;
}
