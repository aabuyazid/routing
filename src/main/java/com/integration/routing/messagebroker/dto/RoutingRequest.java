package com.integration.routing.messagebroker.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class RoutingRequest {
    private int supergwId;
    private String receiver;
    private String sender;
    private LocalDateTime dateReceived;
    private String payload;
}
