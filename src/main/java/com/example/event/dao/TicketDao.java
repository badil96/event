package com.example.event.dao;

import com.example.event.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketDao {

    List<Ticket> selectAllTickets ();

    UUID insertTicket (UUID id, UUID eventId, Ticket ticket);

    default UUID insertTicket(UUID eventId, Ticket ticket) {
        UUID id = UUID.randomUUID();
        return insertTicket(id, eventId, ticket);
    }

    Optional<Ticket> selectTicketById (UUID id);
}
