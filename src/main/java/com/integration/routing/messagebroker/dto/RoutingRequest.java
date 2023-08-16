package com.integration.routing.messagebroker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoutingRequest {
    private int supergwId;
    private String receiver;
    private String sender;
    private LocalDateTime dateReceived;
    private String payload;
}
