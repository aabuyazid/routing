package com.integration.routing.event;

import lombok.Data;
//TODO replace this with a proper library
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Date;

@Data
public class RoutingEvent {
    private int id;
    private Date dateReceived;
    private Date dateSent;
    private String sender; //This should be some number or key
    private String receiver; //This should be some number or key
    private JSONObject payload;
}
