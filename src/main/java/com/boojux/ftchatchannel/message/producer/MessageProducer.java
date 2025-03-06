package com.boojux.ftchatchannel.message.producer;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;
    @Resource
    private Gson gson;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->{
            if (ack) {
                System.out.println("消息发送成功");
            } else {
                System.out.println("消息发送失败: " + cause);
            }
        });

    }

    public void sendMessage(String exchange, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, gson.toJson(message));
    }
}
