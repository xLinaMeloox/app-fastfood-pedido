package br.com.appfastfood.produto.usecase.adaptadores;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.repositorios.ProdutoRepositorio;
import br.com.appfastfood.produto.exceptions.ExceptionsMessages;
import br.com.appfastfood.produto.usecase.portas.ProdutoServico;

import java.util.List;

public class ProdutoServicoImpl implements ProdutoServico {

    private final ProdutoRepositorio produtoRepositorio;

    public ProdutoServicoImpl(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    @Override
    public void cadastrar(Produto produto) {
        this.produtoRepositorio.cadastrar(produto);
    }

    @Override
    public void remover(Long id) {
        Produto buscarProdutoId = buscaProdutoPorId(id);
        if (buscarProdutoId != null){
            this.produtoRepositorio.remover(id);
        }else{
            throw new BadRequestException(ExceptionsMessages.ID_NAO_ENCONTRADO.getValue());
        }
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {
        Produto produtoAlterado = this.produtoRepositorio.atualizar(id, produto);
        return produtoAlterado;
    }

    @Override
    public List<Produto> buscarPorCategoria(String categoria) {
        return this.produtoRepositorio.buscarPorCategoria(categoria).get();
    }

    @Override
    public Produto buscaProdutoPorId(Long id){
        return this.produtoRepositorio.buscarProdutoPorId(id);
    }
 
}
