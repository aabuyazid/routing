package com.integration.routing.messagebroker.consumer;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RoutingRequestProcessor{
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingRequestProcessor.class);

    @RabbitListener(queues = {"${rabbitmq.routing.request.queue}"})
    public void receive(JSONObject jsonObject) {
        LOGGER.info(String.format("Received message from queue: %s", jsonObject.toString()));
    }

}
