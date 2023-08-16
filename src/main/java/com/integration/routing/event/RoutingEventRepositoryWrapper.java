package com.integration.routing.event;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class RoutingEventRepositoryWrapper {

    @Autowired
    private RoutingEventRepository routingEventRepository;

    public void insertRoutingEvent(RoutingEvent routingEvent) {
        routingEventRepository.insertRoutingEvent(
                routingEvent.getSupergwId(), routingEvent.getDateReceived(), LocalDateTime.now(),
                routingEvent.getReceiver(), routingEvent.getSender(), routingEvent.getPayload()
        );
    }
}
