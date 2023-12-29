package br.com.appfastfood.produto.dominio.modelo;

import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.vo.Descricao;
import br.com.appfastfood.produto.dominio.vo.Nome;
import br.com.appfastfood.produto.dominio.vo.Preco;
import br.com.appfastfood.produto.dominio.vo.UriImagem;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ProdutoTest {

    @Test
    public void testGetters() {
        Nome nome = mock(Nome.class);
        Preco preco = mock(Preco.class);
        UriImagem uriImagem = mock(UriImagem.class);
        CategoriaEnum categoria = CategoriaEnum.bebida;
        Descricao descricao = mock(Descricao.class);

        Produto produto = new Produto(null, nome, preco, uriImagem, categoria, descricao);

        assertEquals(nome, produto.getNome());
        assertEquals(preco, produto.getPreco());
        assertEquals(uriImagem, produto.getUriImagem());
        assertEquals(categoria, produto.getCategoria());
        assertEquals(descricao, produto.getDescricao());
    }
}
