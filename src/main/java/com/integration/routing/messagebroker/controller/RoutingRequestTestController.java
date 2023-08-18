package com.integration.routing.messagebroker.controller;

import com.integration.routing.messagebroker.dto.RoutingRequest;
import com.integration.routing.messagebroker.producer.RoutingRequestTestProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/routing/request")
public class RoutingRequestTestController {

    private RoutingRequestTestProducer routingRequestTestProducer;

    public RoutingRequestTestController(RoutingRequestTestProducer routingRequestTestProducer) {
        this.routingRequestTestProducer = routingRequestTestProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendTestMessage(@RequestBody RoutingRequest routingRequest) {
        routingRequestTestProducer.sendTestMessage(routingRequest);
//        return ResponseEntity.ok("Test Routing Request sent to the queue");
        return ResponseEntity.ok("Routing Request sent to the queue");
    }
}
