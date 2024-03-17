package br.com.appfastfood.pedido.usecase.adaptadores.producers;
import org.springframework.stereotype.Component;

import br.com.appfastfood.pedido.usecase.portas.TopicHandler;

@Component
public class PedidoQueueAdapterOUT {
    private TopicHandler snsTopic;
    public PedidoQueueAdapterOUT(TopicHandler snstopicHandler) {
        this.snsTopic = snstopicHandler;
    }
   
    public void criar(Long id) {
        snsTopic.publish(id.toString(), "arn:aws:sns:us-east-1:000000000000:pedido-criado");
    }
    
    public void pedidoPreparado(Long id) {
        snsTopic.publish(id.toString(), "arn:aws:sns:us-east-1:000000000000:pedido-preparado");
    }

    public void cancelaPedido(Long id){
        snsTopic.publish(id.toString(), "arn:aws:sns:us-east-1:000000000000:cancela-pedido");
    }

    public void pedidoFinalizado(Long id){
        snsTopic.publish(id.toString(), "arn:aws:sns:us-east-1:000000000000:pedido-finalizado");
    }
}