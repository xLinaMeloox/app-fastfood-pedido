package br.com.appfastfood.pedido.usecase.adaptadores.consumers;

import br.com.appfastfood.pedido.usecase.portas.TopicHandler;

public class PedidoFinalizadoTopicHandler implements TopicHandler {

    @Override
    public void publish(String message, String topicAddress) {
    }
    
}
