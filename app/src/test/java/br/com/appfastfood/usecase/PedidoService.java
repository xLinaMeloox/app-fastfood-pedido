package br.com.appfastfood.usecase;

import br.com.appfastfood.produto.dominio.modelos.Produto;

public class PedidoService {
    public String criarPedido(Produto produto){
        return "Pedido criado com sucesso";
    }
    public String atualizarStatusPagamento(Boolean pedidoAprovado){
        return "Aprovado";
    }

    public String atualizarStatusProducao(){
        return "Em Produção";
    }
    public String atualizarStatusCancelado(){
        return "Cancelado";
    }

    public String atualizarStatusPronto(){
        return "Pronto";
    }
    public String atualizarStatusFinalizado(){
        return "Finalizado";
    }
}
