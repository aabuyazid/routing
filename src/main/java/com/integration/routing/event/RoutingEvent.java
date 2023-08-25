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
@Table(name = "routing_event_history")
public class RoutingEvent {
    @Column(name = "routing_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routingId;

    @Column(name = "supergw_id", nullable = false)
    private final int supergwId;

    @Column(name = "datetime_received", nullable = false, columnDefinition = "TIMESTAMP")
    private final LocalDateTime dateReceived;

    @Column(name = "datetime_sent", nullable = false, columnDefinition = "TIMESTAMP")
    private final LocalDateTime dateSent;

    @Column(name = "sender", nullable = false)
    private final String sender; //This should be some number or key

    @Column(name = "receiver", nullable = false)
    private final String receiver; //This should be some number or key

    @Column(name = "payload")
    // JSON Object serialized into a string
    private final String payload;
}
