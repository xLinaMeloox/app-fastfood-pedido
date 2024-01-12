package br.com.appfastfood.produto.dominio.repositorios;

import br.com.appfastfood.produto.dominio.modelos.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositorio {
    /** cadastrar,
     * remover,
     * atualizar,
     * listar produtos por categoria
     */
    void cadastrar (Produto produto);
    void remover (Long id);
    Produto atualizar(Long id, Produto produtos);
    Optional<List<Produto>> buscarPorCategoria(String categoria);
    Produto buscarProdutoPorId(Long id);
} 
