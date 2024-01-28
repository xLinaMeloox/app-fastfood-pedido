package br.com.appfastfood.produto.dominio.modelos;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.vo.*;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import br.com.appfastfood.produto.exceptions.ExceptionsMessages;

public class Produto {
    private Long id;
    private String nome;
    private Double preco;
    private String uriImagem;
    private CategoriaEnum categoria;
    private String descricao;

    public Produto(Long id, String nome, Double preco, String uriImagem, CategoriaEnum categoria, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.uriImagem = uriImagem;
        this.categoria = categoria;
        this.descricao = descricao;
    }
    public void validarCampos(Nome nome, Preco preco, UriImagem uriImagem, CategoriaEnum categoria, Descricao descricao) {
        if(Validacoes.validaCamposVaziosOuNulos(nome, preco, uriImagem, categoria, descricao)){
            throw new BadRequestException(ExceptionsMessages.CAMPOS_OBRIGATORIOS.getValue());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUriImagem() {
        return uriImagem;
    }

    public void setUriImagem(String uriImagem) {
        this.uriImagem = uriImagem;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
