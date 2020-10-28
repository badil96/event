package com.example.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;


public class Event {

    @Id
    private UUID id;
    private String name;
    private String organizer;
    private Date eventDate;
    private Time eventTime;
    private String venue;
    private Integer guestsNo;
    private Integer ticketPrice;
    private String type;
    private String status;
    private String eventDetails;
    private Integer availableSeats;
    @MappedCollection
    private Set<Ticket> tickets = new HashSet<>();

    public Event(@JsonProperty("id") UUID id,
                 @JsonProperty("name") String name,
                 @JsonProperty("organizer") String organizer,
                 @JsonProperty("eventDate") Date eventDate,
                 @JsonProperty("eventTime") Time eventTime,
                 @JsonProperty("venue") String venue,
                 @JsonProperty("guestsNo") Integer guestsNo,
                 @JsonProperty("ticketPrice") Integer ticketPrice,
                 @JsonProperty("type") String type,
                 @JsonProperty("status") String status,
                 @JsonProperty("eventDetails") String eventDetails,
                 @JsonProperty("availableSeats") Integer availableSeats)
    {
        this.id = id;
        this.name = name;
        this.organizer = organizer;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.guestsNo = guestsNo;
        this.ticketPrice = ticketPrice;
        this.type = type;
        this.status = status;
        this.eventDetails = eventDetails;
        this.availableSeats = availableSeats;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrganizer() {
        return organizer;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public String getVenue() {
        return venue;
    }

    public Integer getGuestsNo() {
        return guestsNo;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public Set<Ticket> getTickets() {
        return this.tickets;
    }
}
