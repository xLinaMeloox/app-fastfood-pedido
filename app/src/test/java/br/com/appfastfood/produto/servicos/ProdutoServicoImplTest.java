package br.com.appfastfood.produto.servicos;

import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.repositorios.ProdutoRepositorio;
import br.com.appfastfood.produto.dominio.vo.*;
import br.com.appfastfood.produto.usecase.adaptadores.ProdutoServicoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoServicoImplTest {

    private ProdutoRepositorio produtoRepositorio;
    private ProdutoServicoImpl produtoServico;

    @BeforeEach
    void setUp() {
        produtoRepositorio = Mockito.mock(ProdutoRepositorio.class);
        produtoServico = new ProdutoServicoImpl(produtoRepositorio);
    }

    @Test
    void cadastrar_DeveChamarMetodoCadastrarDoRepositorio() {
        // Arrange
        Produto produto = new Produto(1L,
                new Nome("Exemplo de Produto"),
                new Preco(10.99D),
                new UriImagem("https://exemplo.com/imagem.jpg"),
                new Categoria("lanche").getCategoria(),
                new Descricao("Descrição do exemplo de produto")
        );

        // Act
        produtoServico.cadastrar(produto);

        // Assert
        Mockito.verify(produtoRepositorio).cadastrar(produto);
    }

    @Test
    void atualizar_DeveChamarMetodoAtualizarDoRepositorio_ComIdEProdutoInformados() {
        // Arrange
        Long id = 1L;
        Produto produto = new Produto(
                id,
                new Nome("Exemplo de Produto"),
                new Preco(10.99D),
                new UriImagem("https://exemplo.com/imagem.jpg"),
                new Categoria("lanche").getCategoria(),
                new Descricao("Descrição do exemplo de produto")
        );

        Produto produtoAlterado = new Produto(
            id,
                new Nome("Exemplo de Produto Alterado"),
                new Preco(10.99D),
                new UriImagem("https://exemplo.com/imagem.jpg"),
                new Categoria("lanche").getCategoria(),
                new Descricao("Descrição do exemplo de produto")
        );

        Mockito.when(produtoRepositorio.atualizar(id, produto)).thenReturn(produtoAlterado);

        // Act
        Produto resultado = produtoServico.atualizar(id, produto);

        // Assert
        Mockito.verify(produtoRepositorio).atualizar(id, produto);
        assertEquals(produtoAlterado, resultado);
    }

    @Test
    void buscarPorCategoria_DeveRetornarListaDeProdutosDoRepositorioPorCategoria() {
        // Arrange
        String categoria = "lanche";
        List<Produto> produtos = Arrays.asList(new Produto(
            1L,
                new Nome("Exemplo de Produto Alterado"),
                new Preco(10.99D),
                new UriImagem("https://exemplo.com/imagem.jpg"),
                new Categoria("lanche").getCategoria(),
                new Descricao("Descrição do exemplo de produto")
        ));

        Mockito.when(produtoRepositorio.buscarPorCategoria(categoria)).thenReturn(Optional.of(produtos));

        // Act
        List<Produto> resultado = produtoServico.buscarPorCategoria(categoria);

        // Assert
        Mockito.verify(produtoRepositorio).buscarPorCategoria(categoria);
        assertEquals(produtos, resultado);
    }
}
