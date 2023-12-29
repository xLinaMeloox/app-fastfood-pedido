package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import br.com.appfastfood.produto.exceptions.ExceptionsMessages;

public class Categoria {
    private CategoriaEnum categoria;
    public Categoria(String categoria) {
        this.categoria = this.isValid(categoria);
    }
    public CategoriaEnum getCategoria() {
        return categoria;
    }
    private CategoriaEnum isValid(String categoria) {
        return this.existCategoria(categoria);
    }

    private CategoriaEnum existCategoria(String categoria) {
        try {
            return CategoriaEnum.valueOf(categoria);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ExceptionsMessages.CATEGORIA_NAO_ENCONTRADA.getValue());
        }
    }


}
