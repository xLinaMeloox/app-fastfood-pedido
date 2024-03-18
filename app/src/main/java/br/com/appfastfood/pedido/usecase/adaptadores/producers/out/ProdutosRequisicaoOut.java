package br.com.appfastfood.pedido.usecase.adaptadores.producers.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder()
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutosRequisicaoOut {

    @JsonProperty("id_produto")
    private String idProduto;

    @JsonProperty("quantidade_produto")
    private String quantidadeProduto;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("preco")
    private Double preco;

    @JsonProperty("categoria")
    private String categoria;

    @JsonProperty("uriImagem")
    private String uriImagem;

}
