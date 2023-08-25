package com.integration.routing.messagebroker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class RoutingResponse {
    private String errorCode;
    private String errorMsg;
    private int routingId;
}
