package br.com.appfastfood.pedido.usecase.portas;

//import com.amazonaws.services.sns.message.SnsMessage;


public interface TopicHandler {

    void publish(String message, String topicAddress);

}
