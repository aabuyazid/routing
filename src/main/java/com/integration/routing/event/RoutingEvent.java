package com.integration.routing.event;

import jakarta.persistence.*;
import lombok.*;
//TODO replace this with a proper library
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "RoutingEventHistory")
public class RoutingEvent {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DateReceived", nullable = false)
    private Date dateReceived;

    @Column(name = "DateSent", nullable = false)
    private Date dateSent;

    @Column(name = "Sender", nullable = false)
    private String sender; //This should be some number or key

    @Column(name = "Receiver", nullable = false)
    private String receiver; //This should be some number or key

    @Column(name = "Payload")
    private JSONObject payload;
}
