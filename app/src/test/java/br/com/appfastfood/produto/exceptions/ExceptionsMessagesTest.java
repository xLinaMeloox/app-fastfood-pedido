package br.com.appfastfood.produto.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionsMessagesTest {

    @Test
    void testExceptionsMessagesValues() {
        // Verifica se os valores das mensagens de exceção correspondem ao esperado
        assertEquals("Todos os campos são obrigatórios!", ExceptionsMessages.CAMPOS_OBRIGATORIOS.getValue());
        assertEquals("Categoria não existente!", ExceptionsMessages.CATEGORIA_NAO_ENCONTRADA.getValue());
        assertEquals("Id Produto não encontrado!", ExceptionsMessages.ID_NAO_ENCONTRADO.getValue());
        assertEquals("URI com formato inválido!", ExceptionsMessages.URI_IMAGEM_FORMATO_INVALIDO.getValue());
        assertEquals("URI é um campo obrigatório!", ExceptionsMessages.URI_IMAGEM_OBRIGATORIO.getValue());
    }
}
