package com.example.event.service;

import com.example.event.dao.TicketDao;
import com.example.event.model.Event;
import com.example.event.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketDao ticketDao;

    @Autowired
    public TicketService(@Qualifier("postgresDao") TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public UUID addTicket(UUID eventId, Ticket ticket) {
        return ticketDao.insertTicket(eventId, ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketDao.selectAllTickets();
    }

    public Optional<Ticket> getTicketById(UUID id) {
        return ticketDao.selectTicketById(id);
    }
}
