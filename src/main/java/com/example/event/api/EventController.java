package com.example.event.api;

import com.example.event.model.Event;
import com.example.event.model.Ticket;
import com.example.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequestMapping("api/v1/event")
@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping(path = "/{id}")
    public Event getEventById(@PathVariable("id")UUID id){
        return eventService.getEventById(id)
                .orElse(null);
    }

    @GetMapping(path = "{id}/tickets")
    public Set<Ticket> getEventTickets(@PathVariable("id") UUID id) {
        return eventService.getTickets(id);
    }

    @PostMapping
    public int addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @PutMapping (path = "/{id}")
    public int updateEvent(@PathVariable UUID id,@RequestBody Event event){
        return eventService.updateEvent(id, event);
    }

    @PostMapping(path = "/{id}/book")
    public int bookTicket(@PathVariable UUID id, @RequestBody Ticket ticket) {
        return eventService.addTicket(ticket, id);
    }
}
