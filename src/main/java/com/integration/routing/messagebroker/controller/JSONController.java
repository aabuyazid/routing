package com.integration.routing.messagebroker.controller;

import com.integration.routing.messagebroker.dto.User;
import com.integration.routing.messagebroker.producer.RabbitMQJSONProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/json/v1")
public class JSONController {

    private RabbitMQJSONProducer rabbitMQJSONProducer;

    public JSONController(RabbitMQJSONProducer rabbitMQJSONProducer) {this.rabbitMQJSONProducer = rabbitMQJSONProducer;}

    @PostMapping("/publish")
    public ResponseEntity<String> sendJSONMessage(@RequestBody User user) {
        rabbitMQJSONProducer.sendMessage(user);
        return ResponseEntity.ok("JSON Message sent to RabbitMQ");
    }
}
