package br.com.appfastfood.produto.dominio.vo.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaEnumTest {

    @Test
    void testCategoriaEnumValues() {
        // Verifica se os valores da enumeração correspondem ao esperado
        assertEquals(CategoriaEnum.lanche, CategoriaEnum.valueOf("lanche"));
        assertEquals(CategoriaEnum.sobremesa, CategoriaEnum.valueOf("sobremesa"));
        assertEquals(CategoriaEnum.bebida, CategoriaEnum.valueOf("bebida"));
        assertEquals(CategoriaEnum.todos, CategoriaEnum.valueOf("todos"));
    }
}
