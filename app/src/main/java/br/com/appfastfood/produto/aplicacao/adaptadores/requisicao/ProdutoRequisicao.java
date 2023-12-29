package br.com.appfastfood.produto.aplicacao.adaptadores.requisicao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
@Builder()
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoRequisicao {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("preco")
    private Double preco;

    @JsonProperty("uri_imagem")
    private String uriImagem;

    @JsonProperty("categoria")
    private String categoria;

    @JsonProperty("descricao")
    private String descricao;
}
