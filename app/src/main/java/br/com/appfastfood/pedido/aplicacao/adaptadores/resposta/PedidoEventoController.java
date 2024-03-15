package br.com.appfastfood.pedido.aplicacao.adaptadores.resposta;

import org.aspectj.bridge.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.usecase.adaptadores.IPedidoQueueAdapterOUT;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;

@RestController
public class PedidoEventoController implements IPedidoQueueAdapterOUT {
    
     @Autowired
    private PedidoServico pedidoServico;

    @Autowired
    private RabbitTemplate rabbitTemplate; 
    public ResponseEntity<PedidoRequisicao> criar() {
        // Cria o pedido
        String id = this.pedidoServico.criar();

        // Envia o pedido para a fila RabbitMQ
        publish(id);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(PedidoRequisicao.builder().idPedido(id).build());
    }


    @Override
    public void publish(String idPedido) {
         rabbitTemplate.convertAndSend("nomeDaFila", idPedido);
    }
}
