package com.integration.routing.messagebroker.producer;

import com.integration.routing.event.RoutingEvent;
import com.integration.routing.messagebroker.consumer.RoutingRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoutingResponseProducer {

    @Value("${rabbitmq.routing.response.queue}")
    private String queue;

    @Value("${rabbitmq.routing.response.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing.response.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingResponseProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RoutingResponseProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendResponse(RoutingEvent routingEvent) {
        LOGGER.info(String.format("Sending routingEvent %s to response queue", routingEvent.getRoutingId()));
        rabbitTemplate.convertAndSend(exchange, routingKey, routingEvent);
    }
}
