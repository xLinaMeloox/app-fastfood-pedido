package br.com.appfastfood.configuracoes.client.carrinho;

import java.util.List;

public record Carrinho(
        Long id,
        String status,
        List<Produto> produtos,
        String idCliente,
        Double valorTotal
) {

    public record Produto(
            String idProduto,
            Integer quantidadeProduto
    ) {

    }

    public Long getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
}
