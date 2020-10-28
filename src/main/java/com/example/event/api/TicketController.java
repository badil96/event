package com.example.event.api;

import com.example.event.model.Event;
import com.example.event.model.Ticket;
import com.example.event.service.EventService;
import com.example.event.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping(path = "/{id}")
    public Ticket getTicketById(@PathVariable("id") UUID id){
        return ticketService.getTicketById(id)
                .orElse(null);
    }

    @PostMapping(path = "/{eventId}")
    public UUID addTicket(@PathVariable UUID eventId,@RequestBody Ticket ticket){
        return ticketService.addTicket(eventId,ticket);
    }

}
