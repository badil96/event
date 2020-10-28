package com.example.event.dao;

import com.example.event.model.Event;
import com.example.event.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface EventDao {

    List<Event> selectAllEvents ();

    int insertEvent (UUID id, Event event);

    default int insertEvent(Event event) {
        UUID id = UUID.randomUUID();
        return insertEvent(id, event);
    }

    Optional<Event> selectEventById (UUID id);

    int updateEvent (UUID id, Event event);

    public Boolean availableSeats(UUID id);

    int addTicket (Ticket ticket, UUID id);

    Set<Ticket> getEventTickets (UUID id);

}
