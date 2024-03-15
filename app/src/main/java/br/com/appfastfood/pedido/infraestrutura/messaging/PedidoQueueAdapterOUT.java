package br.com.appfastfood.pedido.infraestrutura.messaging;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mongodb.internal.diagnostics.logging.Logger;

import br.com.appfastfood.pedido.usecase.adaptadores.IPedidoQueueAdapterOUT;

@Service
public class PedidoQueueAdapterOUT implements IPedidoQueueAdapterOUT {
   
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${queue1.name}")
    private String pedidosPendentes;

    @Override
    public void publish(String message){
        rabbitTemplate.convertAndSend(pedidosPendentes,message);
    }
    
}