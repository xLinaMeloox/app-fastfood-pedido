package br.com.appfastfood.produto.dominio.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NomeTest {

    @Test
    void getNome_DeveRetornarNomeCorreto() {
        String nome = "Pizza";
        Nome objetoNome = new Nome(nome);

        assertEquals(nome, objetoNome.getNome());
    }
}

