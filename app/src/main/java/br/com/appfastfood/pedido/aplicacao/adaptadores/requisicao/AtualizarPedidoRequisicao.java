package br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;


public class AtualizarPedidoRequisicao {
    private Long id;
    private StatusPedidoEnum status;

    
    public Long getId() {
        return id;
    }
    public StatusPedidoEnum getStatus() {
        return status;
    }



    
}
