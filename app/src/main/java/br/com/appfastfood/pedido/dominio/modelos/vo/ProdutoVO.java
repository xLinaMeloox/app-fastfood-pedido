package br.com.appfastfood.pedido.dominio.modelos.vo;

public class ProdutoVO {
    private String idProduto;
    private String quantidadeProduto;
    private String nome;
    private Double preco;
    private String categoria;
    private String uriImagem;

    public ProdutoVO(String idProduto, String quantidadeProduto) {
        this.idProduto = idProduto;
        this.quantidadeProduto = quantidadeProduto;
    }

    public ProdutoVO(String idProduto, String quantidadeProduto, String nome, Double preco, String categoria,
            String uriImagem) {
        this.idProduto = idProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.uriImagem = uriImagem;
    }
    
    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public void setQuantidadeProduto(String quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUriImagem() {
        return uriImagem;
    }

    public void setUriImagem(String uriImagem) {
        this.uriImagem = uriImagem;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public String getQuantidadeProduto() {
        return quantidadeProduto;
    }
}
