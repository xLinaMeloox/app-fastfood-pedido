package br.com.appfastfood.produto.exceptions;

public enum ExceptionsMessages {

    CAMPOS_OBRIGATORIOS("Todos os campos são obrigatórios!"),
    CATEGORIA_NAO_ENCONTRADA("Categoria não existente!"),
    ID_NAO_ENCONTRADO("Id Produto não encontrado!"),
    URI_IMAGEM_FORMATO_INVALIDO("URI com formato inválido!"),
    URI_IMAGEM_OBRIGATORIO("URI é um campo obrigatório!");

    private final String value;

    ExceptionsMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

