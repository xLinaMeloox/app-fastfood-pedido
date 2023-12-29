package br.com.appfastfood.produto.dominio.vo;

import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;

public class Validacoes {
    public static boolean validaTamanhoMaximoDoCampo(String campo, int i) {
        return campo.length() > i;
    }

    public static boolean validaCamposVaziosOuNulos(Nome nome, Preco preco, UriImagem uriImagem, CategoriaEnum categoria, Descricao descricao) {
        return estaBrancoOuNulo(nome.getNome()) &&
                uriImagem.getUriImagem() == null &&
                preco.getPreco() == null &&
                estaBrancoOuNulo(uriImagem.getUriImagem()) &&
                estaBrancoOuNulo(categoria.name()) &&
                estaBrancoOuNulo(descricao.getDescricao());
    }

    private static boolean estaBrancoOuNulo(String campo) {
        return campo == null || campo.isBlank();
    }
}
