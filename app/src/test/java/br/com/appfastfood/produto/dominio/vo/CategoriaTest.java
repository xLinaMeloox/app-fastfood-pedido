package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoriaTest {

    @Test
    void construtor_DeveLancarExcecao_QuandoCategoriaNaoExistir() {
        String categoriaNaoExistente = "CategoriaInexistente";
        assertThrows(BadRequestException.class, () -> new Categoria(categoriaNaoExistente));
    }

    @Test
    void getCategoria_DeveRetornarCategoriaCorreta() {
        String categoriaExistente = "bebida";
        Categoria objetoCategoria = new Categoria(categoriaExistente);

        assertEquals(CategoriaEnum.bebida, objetoCategoria.getCategoria());
    }
}
