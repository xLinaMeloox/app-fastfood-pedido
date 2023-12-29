package br.com.appfastfood.produto.usecase.portas;

import br.com.appfastfood.produto.dominio.modelos.Produto;

import java.util.List;

public interface ProdutoServico {
    void cadastrar (Produto produto);
    void remover (Long id);
    Produto atualizar(Long id, Produto produto);
    List<Produto> buscarPorCategoria(String categoria);
    Produto buscaProdutoPorId(Long id);
} 
