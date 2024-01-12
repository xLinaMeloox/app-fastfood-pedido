package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.exceptions.ExceptionsMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriImagem {
    private String uriImagem;

    public UriImagem(String uriImagem) {
        isValid(uriImagem);
        this.uriImagem = uriImagem;
    }

    public String getUriImagem() {
        return uriImagem;
    }

    private void isValid(String nome) {
        isUrl(nome);
    }

    private void isUrl(String url) {
        Matcher matcher = Pattern.compile("^(https?|ftp):\\/\\/[^\\s/$.?#].[^\\s]*$").matcher(url);
        if(!matcher.matches()) {
            throw new BadRequestException(ExceptionsMessages.URI_IMAGEM_FORMATO_INVALIDO.getValue());
        }
    }

}
