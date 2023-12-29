package br.com.appfastfood.pedido.infraestrutura.entidades;

import jakarta.persistence.Embeddable;
@Embeddable
public class ProdEnt {

    private String idProduto;

    private String quantidadeProduto;

    public ProdEnt(String idProduto, String quantidadeProduto) {
        this.idProduto = idProduto;
        this.quantidadeProduto = quantidadeProduto;
    }

    protected ProdEnt() {}

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(String quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
}
