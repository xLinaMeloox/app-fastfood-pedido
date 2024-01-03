package br.com.appfastfood.produto.infraestrutura.entidades;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "produto")
public class ProdutoEntidade {

    private Long id;
    protected ProdutoEntidade() {}
    private String nome;
    private Double preco;
    private String uriImagem;
    private String categoria;
    private String descricao;

    public ProdutoEntidade(Long id, String nome, Double preco, String uriImagem, String categoria, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.uriImagem = uriImagem;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public ProdutoEntidade(String nome, Double preco, String uriImagem, String categoria, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.uriImagem = uriImagem;
        this.categoria = categoria;
        this.descricao = descricao;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public String getUriImagem() {
        return uriImagem;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }
}

