package br.com.appfastfood.produto.dominio.modelos;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.vo.*;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import br.com.appfastfood.produto.exceptions.ExceptionsMessages;

public class Produto {
    private Long id;
    private Nome nome;
    private Preco preco;
    private UriImagem uriImagem;
    private CategoriaEnum categoria;
    private Descricao descricao;

    public Produto(Long id, Nome nome, Preco preco, UriImagem uriImagem, CategoriaEnum categoria, Descricao descricao) {
        this.id = id;
        this.validarCampos(nome, preco, uriImagem, categoria, descricao);
        this.nome = nome;
        this.preco = preco;
        this.uriImagem = uriImagem;
        this.categoria = categoria;
        this.descricao = descricao;
    }
    private void validarCampos(Nome nome, Preco preco, UriImagem uriImagem, CategoriaEnum categoria, Descricao descricao) {
        if(Validacoes.validaCamposVaziosOuNulos(nome, preco, uriImagem, categoria, descricao)){
            throw new BadRequestException(ExceptionsMessages.CAMPOS_OBRIGATORIOS.getValue());
        }
    }

    public Long getId() {
        return id;
    }

    public Nome getNome() {
        return nome;
    }

    public Preco getPreco() {
        return preco;
    }

    public UriImagem getUriImagem() {
        return uriImagem;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public Descricao getDescricao() {
        return descricao;
    }

}
