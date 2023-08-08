package com.integration.routing.event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONObject;

import java.time.LocalDateTime;

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
    private LocalDateTime dateReceived;

    @Column(name = "DateSent", nullable = false)
    private LocalDateTime dateSent;

    @Column(name = "Sender", nullable = false)
    private String sender; //This should be some number or key

    @Column(name = "Receiver", nullable = false)
    private String receiver; //This should be some number or key

    @Column(name = "Payload")
    // JSON Object serialized into a string
    private String payload;

    void setPayloadJSON(JSONObject jsonObject) {
        String payload = jsonObject.toString();
        setPayload(payload);
    }
}
