package com.integration.routing.messagebroker.consumer;

import com.integration.routing.messagebroker.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSONConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void consumeJSON(String message) {
        LOGGER.info(String.format("Received message -> \n %s", message));
    }
}
