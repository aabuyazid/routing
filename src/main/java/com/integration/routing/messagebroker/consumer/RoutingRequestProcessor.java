package com.integration.routing.messagebroker.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.routing.event.RoutingEvent;
import com.integration.routing.event.RoutingEventRepository;
import com.integration.routing.event.RoutingEventRepositoryWrapper;
import com.integration.routing.messagebroker.dto.RoutingRequest;
import com.integration.routing.messagebroker.producer.RoutingResponseProducer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class RoutingRequestProcessor{
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingRequestProcessor.class);

    @Autowired
    private RoutingResponseProducer routingResponseProducer;

    @Autowired
    private RoutingEventRepositoryWrapper routingEventRepositoryWrapper;

    @Autowired
    private Jackson2ObjectMapperBuilderCustomizer jsonCustomizer;

    @RabbitListener(queues = {"${rabbitmq.routing.request.queue}"})
    public void receive(RoutingRequest routingRequest) {
        LOGGER.info(String.format("Received message from queue: %s", routingRequest.toString()));

        RoutingEvent routingEvent = new RoutingEvent(routingRequest.getSupergwId(), routingRequest.getDateReceived(),
                LocalDateTime.now(), routingRequest.getReceiver(), routingRequest.getSender(), routingRequest.getPayload());

        routingEventRepositoryWrapper.insertRoutingEvent(routingEvent);

        routingResponseProducer.sendResponse(routingEvent);
    }

}
