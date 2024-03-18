package br.com.appfastfood.pedido.usecase.adaptadores;


import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import br.com.appfastfood.pedido.usecase.portas.TopicHandler;

public class SNSTopicHandlerImpl implements TopicHandler {
    private final AmazonSNS snsClient;

    public SNSTopicHandlerImpl(AmazonSNS snsClient) {
        this.snsClient = snsClient;
    }

    @Override
    public void publish(String message, String topicArn) {
        PublishRequest publishRequest = new PublishRequest(topicArn, message);
        try {
            PublishResult publishResult = snsClient.publish(publishRequest);
            System.out.println("Message published to the topic " + topicArn + " with message ID " + publishResult.getMessageId());
        } catch (Exception e) {
            System.err.println("Error publishing message to SNS: " + e.getMessage());
        }
    }


}
