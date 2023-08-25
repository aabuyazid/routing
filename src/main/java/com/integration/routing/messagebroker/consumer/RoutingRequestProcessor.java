package com.integration.routing.messagebroker.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.routing.event.RoutingEvent;
import com.integration.routing.event.RoutingEventRepository;
import com.integration.routing.messagebroker.dto.RoutingRequest;
import com.integration.routing.messagebroker.dto.RoutingResponse;
import com.integration.routing.messagebroker.producer.RoutingResponseProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoutingRequestProcessor{
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingRequestProcessor.class);

    @Autowired
    private RoutingResponseProducer routingResponseProducer;

    @Autowired
    private RoutingEventRepository routingEventRepository;

    public void insertRoutingEvent(RoutingEvent routingEvent) {
        routingEventRepository.insertRoutingEvent(
                routingEvent.getSupergwId(), routingEvent.getDateReceived(), LocalDateTime.now(),
                routingEvent.getReceiver(), routingEvent.getSender(), routingEvent.getPayload()
        );
    }
//    @RabbitListener(queues = {"${rabbitmq.routing.request.queue}"})
//    public void receive(String message) {
//        LOGGER.info(String.format("Received message from queue: %s", message));
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            RoutingRequest routingRequest = objectMapper.readValue(message, RoutingRequest.class);
//            RoutingEvent routingEvent = new RoutingEvent(routingRequest.getSupergwId(), /*routingRequest.getDateReceived(),*/
//                    LocalDateTime.now(), LocalDateTime.now(), routingRequest.getReceiver(), routingRequest.getSender(), routingRequest.getPayload());
//            insertRoutingEvent(routingEvent);
//            routingResponseProducer.sendResponse(routingEvent);
//            LOGGER.info(String.format("New Routing Event: %s", routingEvent.toString()));
//
//        }
//        catch (Exception e) {
//            LOGGER.error(e.toString());
//
//        }
//        finally {
////            LOGGER.info(String.format("Received message from queue: %s", routingRequest.toString()));
//        }
//    }
    @RabbitListener(queues = {"${rabbitmq.routing.request.queue}"})
    public void receive(RoutingRequest routingRequest) {
        LOGGER.info(String.format("Received message from queue: %s", routingRequest.toString()));
        RoutingResponse routingResponse = new RoutingResponse();
        try {
            routingEventRepository.findAll(Sort.by())
            RoutingEvent routingEvent = new RoutingEvent(routingRequest.getSupergwId(), routingRequest.getDateReceived(),
                    LocalDateTime.now(), routingRequest.getReceiver(), routingRequest.getSender(), routingRequest.getPayload());
            LOGGER.info(String.format("Created Routing Event: %s", routingEvent.toString()));
            insertRoutingEvent(routingEvent);
            routingResponse.setErrorCode("000");
            routingResponse.setErrorMsg("");
        }
        catch (DataAccessException daException) {
            LOGGER.error(String.format("Error occurred when quering table routing_event_history.\n%s", daException.fillInStackTrace()));
            routingResponse.setErrorCode("SQL");
            routingResponse.setErrorMsg(daException.toString());
        }
        catch (AssertionError aeException) {
            LOGGER.error(String.format("Duplicate supergwId: %d", routingRequest.getSupergwId()));
            routingResponse.setErrorCode("DUP");
            routingResponse.setErrorMsg(String.format("Duplicate supergwId: %d", routingRequest.getSupergwId()));
        }
        catch (Exception e) {
            LOGGER.error(String.format("General error occurred.\n%s",e.fillInStackTrace().toString()));
            routingResponse.setErrorCode("UNK");
            routingResponse.setErrorMsg(e.toString());
        }
        finally {
            routingResponseProducer.sendResponse(routingResponse);
        }
    }

}
