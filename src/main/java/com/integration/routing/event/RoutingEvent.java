package com.integration.routing.event;

import jakarta.persistence.*;
import lombok.*;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "RoutingEventHistory")
public class RoutingEvent {
    @Column(name = "RoutingId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routingId;

    @Column(name = "SupergwId", nullable = false)
    private final int supergwId;

    @Column(name = "DateReceived", nullable = false, columnDefinition = "TIMESTAMP")
    private final LocalDateTime dateReceived;

    @Column(name = "DateSent", nullable = false, columnDefinition = "TIMESTAMP")
    private final LocalDateTime dateSent;

    @Column(name = "Sender", nullable = false)
    private final String sender; //This should be some number or key

    @Column(name = "Receiver", nullable = false)
    private final String receiver; //This should be some number or key

    @Column(name = "Payload")
    // JSON Object serialized into a string
    private final String payload;
}
