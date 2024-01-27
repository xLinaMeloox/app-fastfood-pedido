package br.com.appfastfood.usecase;

import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;

public class CarrinhoService {
        public boolean isCarrinhoFechado() {
            return true;
        }

        public Produto obterProdutos() {
            return new Produto(
                1L,
                "X-Salada",
                10.0,
                "http://imagem.com/xsalada.jpg",
                CategoriaEnum.lanche,
                "Delicioso"
                );
        }

    }

