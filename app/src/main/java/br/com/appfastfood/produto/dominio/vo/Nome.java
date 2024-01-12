package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.exceptions.ExceptionsMessages;

public class Nome {
    private String nome;
    public Nome(String nome) {
        this.isValid(nome);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    private void isValid(String nome) {
        if(nome == null || nome.isEmpty()) {
            throw new BadRequestException(ExceptionsMessages.CAMPOS_OBRIGATORIOS.getValue());
        }
    }


}
