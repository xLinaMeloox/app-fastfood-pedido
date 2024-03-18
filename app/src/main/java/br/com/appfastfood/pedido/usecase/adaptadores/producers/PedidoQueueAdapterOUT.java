package br.com.appfastfood.pedido.usecase.adaptadores.producers;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.usecase.portas.TopicHandler;

@Component
public class PedidoQueueAdapterOUT {
    private TopicHandler snsTopic;
    public PedidoQueueAdapterOUT(TopicHandler snstopicHandler) {
        this.snsTopic = snstopicHandler;
    }
   
    public void criar(Pedido pedido) {
        try {
            snsTopic.publish(new ObjectMapper().writeValueAsString(pedido), "arn:aws:sns:us-east-1:000000000000:pedido-criado");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void pedidoPreparado(Pedido pedido) {
        try {
            snsTopic.publish(new ObjectMapper().writeValueAsString(pedido), "arn:aws:sns:us-east-1:000000000000:pedido-preparado");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelaPedido(Pedido pedido){
        try {
            snsTopic.publish(new ObjectMapper().writeValueAsString(pedido), "arn:aws:sns:us-east-1:000000000000:pedido-finalizado");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void pedidoFinalizado(Pedido pedido){
        try {
            snsTopic.publish(new ObjectMapper().writeValueAsString(pedido), "arn:aws:sns:us-east-1:000000000000:pedido-finalizado");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
    }
}