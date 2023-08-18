package com.integration.routing.messagebroker.producer;

import com.integration.routing.event.RoutingEvent;
import com.integration.routing.messagebroker.dto.RoutingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoutingRequestTestProducer {

    @Value("${rabbitmq.routing.request.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing.request.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingRequestTestProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RoutingRequestTestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTestMessage(RoutingRequest routingRequest) {
        LOGGER.info(String.format("Message sent -> %s", routingRequest.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, routingRequest.toString());
    }
}
