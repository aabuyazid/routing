package com.integration.routing.event;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RoutingEventRepository extends JpaRepository<RoutingEvent, Long> {

    @Query(value = "SELECT r FROM RoutingEvent r WHERE r.supergw_id = :supergwId")
    List<RoutingEvent> findBySupergwId(@Param("supergwId") int supergwId);
    @Modifying
    @Transactional
    @Query(
        value =
            "INSERT INTO routing_event_history (supergw_id, datetime_received, datetime_sent, receiver, sender, payload) VALUES (:supergwId, :dateReceived, :dateSent, :receiver, :sender, :payload)",
        nativeQuery = true)
    void insertRoutingEvent(@Param("supergwId") int supergwId, @Param("dateReceived") LocalDateTime dateReceived,
                            @Param("dateSent") LocalDateTime dateSent, @Param("receiver") String receiver,
                            @Param("sender") String sender, @Param("payload") String payload);

//    void insertRoutingEvent(RoutingEvent routingEvent) {
//        insertRoutingEvent(routingEvent.getSupergwId(), routingEvent.getDateReceived(), routingEvent.getDateSent(), routingEvent.getReceiver(), routingEvent.getSender(), routingEvent.getPayload());
//    }
}
