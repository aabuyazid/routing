package com.integration.routing.messagebroker.producer;

import com.integration.routing.messagebroker.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSONProducer {
    @Value("${rabbitmq.json.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.json.routingkey.name}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONProducer.class);

    private RabbitTemplate rabbitTemplate;

    // Constructor based injection
    public RabbitMQJSONProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(User user)  {
        LOGGER.info(String.format("Message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, user.toString());
    }

}
