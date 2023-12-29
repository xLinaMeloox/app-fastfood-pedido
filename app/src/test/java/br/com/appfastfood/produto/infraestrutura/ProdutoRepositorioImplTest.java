package br.com.appfastfood.produto.infraestrutura;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.repositorios.ProdutoRepositorio;
import br.com.appfastfood.produto.infraestrutura.entidades.ProdutoEntidade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositorioImplTest {

    @Mock
    private SpringDataProdutoRepository springDataProdutoRepository;

    private ProdutoRepositorio produtoRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoRepositorio = new ProdutoRepositorioImpl(springDataProdutoRepository);
    }

    @Test
    void testBuscarPorCategoria_CategoriaEncontrada() {

        // Mock de dados
        String categoria = "lanche";

        List<ProdutoEntidade> produtoEntidades = new ArrayList<>();
        produtoEntidades.add(new ProdutoEntidade("Produto 1", 10.0D, "http://localhost:8080", "lanche", "Descrição do produto 1"));
        produtoEntidades.add(new ProdutoEntidade("Produto 2", 20.0D, "http://localhost:8080", "lanche", "Descrição do produto 2"));
        Mockito.when(springDataProdutoRepository.findProdutoEntidadeByCategoria(categoria))
                .thenReturn(Optional.of(produtoEntidades));

        // Execução do método a ser testado
        Optional<List<Produto>> resultado = produtoRepositorio.buscarPorCategoria(categoria);

        // Verificação dos resultados
        Assertions.assertTrue(resultado.isPresent());
        List<Produto> produtos = resultado.get();
        Assertions.assertEquals(2, produtos.size());
        Assertions.assertEquals("Produto 1", produtos.get(0).getNome().getNome());
        Assertions.assertEquals(10.0D, produtos.get(0).getPreco().getPreco());
        Assertions.assertEquals("http://localhost:8080", produtos.get(0).getUriImagem().getUriImagem());
        Assertions.assertEquals("lanche", produtos.get(0).getCategoria().name());
        Assertions.assertEquals("Descrição do produto 1", produtos.get(0).getDescricao().getDescricao());
        Assertions.assertEquals("Produto 2", produtos.get(1).getNome().getNome());
        Assertions.assertEquals(20.0D, produtos.get(1).getPreco().getPreco());
        Assertions.assertEquals("http://localhost:8080", produtos.get(1).getUriImagem().getUriImagem());
        Assertions.assertEquals("lanche", produtos.get(1).getCategoria().name());
        Assertions.assertEquals("Descrição do produto 2", produtos.get(1).getDescricao().getDescricao());

        // Verificação de chamadas de métodos
        Mockito.verify(springDataProdutoRepository, Mockito.times(1))
                .findProdutoEntidadeByCategoria(categoria);
    }

    @Test
    void testBuscarPorCategoria_CategoriaNaoEncontrada() {
        // Mock de dados
        String categoria = "Bebidas";
        Mockito.when(springDataProdutoRepository.findProdutoEntidadeByCategoria(categoria))
                .thenReturn(Optional.empty());

        // Execução do método a ser testado e verificação da exceção
        Assertions.assertThrows(BadRequestException.class,
                () -> produtoRepositorio.buscarPorCategoria(categoria));

        // Verificação de chamadas de métodos
        Mockito.verify(springDataProdutoRepository, Mockito.times(1))
                .findProdutoEntidadeByCategoria(categoria);
    }
}