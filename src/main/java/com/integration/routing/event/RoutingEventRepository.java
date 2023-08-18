package com.integration.routing.event;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RoutingEventRepository extends JpaRepository<RoutingEvent, Long> {
    @Query("SELECT rEvent FROM RoutingEvent rEvent")
    List<RoutingEvent> findAllRoutingEvents(Sort sort);

    @Modifying
    @Query(
        value =
            "INSERT INTO RoutingEvent (supergwId, dateTimeReceived, dateTimeSent, receiver, sender, payload) VALUES (:supergwId, :dateReceived, :dateSent, :receiver, :sender, :payload)",
        nativeQuery = true)
    void insertRoutingEvent(@Param("supergwId") int supergwId, @Param("dateReceived") LocalDateTime dateReceived,
                            @Param("dateSent") LocalDateTime dateSent, @Param("receiver") String receiver,
                            @Param("sender") String sender, @Param("payload") String payload);

//    void insertRoutingEvent(RoutingEvent routingEvent) {
//        insertRoutingEvent(routingEvent.getSupergwId(), routingEvent.getDateReceived(), routingEvent.getDateSent(), routingEvent.getReceiver(), routingEvent.getSender(), routingEvent.getPayload());
//    }
}
