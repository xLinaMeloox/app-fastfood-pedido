package br.com.appfastfood.pedido.usecase.adaptadores.producers;
import java.util.List;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import br.com.appfastfood.pedido.usecase.portas.TopicHandler;

public class PedidoQueueAdapterOUT {
   
 
    private TopicHandler snsTopic;
    public PedidoQueueAdapterOUT(TopicHandler snstopicHandler) {
        this.snsTopic = snstopicHandler;
    }
   
    public void criar(Long id) {
        snsTopic.publish(id.toString(), "arn:aws:sns:us-east-1:000000000000:pedido-criado");
    }
    
    public void preparaPedido(Long id) {
        snsTopic.publish(id.toString(), "arn:aws:sns:us-east-1:000000000000:pedido-preparado");
    }

  

    
}