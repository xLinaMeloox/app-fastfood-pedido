package br.com.appfastfood.pedido.usecase.adaptadores;

import org.springframework.messaging.handler.annotation.Payload;

public interface IPedidoQueueAdapterIN {
    
    void receive(@Payload String message);
}
