package com.example.event.service;

import com.example.event.dao.EventDao;
import com.example.event.dao.TicketDao;
import com.example.event.model.Event;
import com.example.event.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class EventService {

    private final EventDao eventDao;
    private final TicketDao ticketDao;

    @Autowired
    public EventService(@Qualifier("postgres") EventDao eventDao, @Qualifier("postgresDao") TicketDao ticketDao) {
        this.eventDao = eventDao;
        this.ticketDao = ticketDao;
    }

    public int addEvent(Event event){
        return eventDao.insertEvent(event);
    }

    public List<Event> getAllEvents(){
        return eventDao.selectAllEvents();
    }

    public Optional<Event> getEventById(UUID id) {
        return eventDao.selectEventById(id);
    }

    public int updateEvent (UUID id, Event event) {
        return eventDao.updateEvent(id, event);
    }

    public int addTicket (Ticket ticket, UUID id) {
        Boolean available = eventDao.availableSeats(id);
        if (available) {
            ticketDao.insertTicket(id, ticket);
            return eventDao.addTicket(ticket, id);
        }
        return 1;
    }

    public Set<Ticket> getTickets (UUID id) {
        return eventDao.getEventTickets(id);
    }
}
