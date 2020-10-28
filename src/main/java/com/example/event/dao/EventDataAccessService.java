package com.example.event.dao;

import com.example.event.model.Event;
import com.example.event.model.Ticket;
import com.example.event.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

@Repository("postgres")
public class EventDataAccessService implements EventDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Event mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String name = resultSet.getString("name");
        String organizer = resultSet.getString("organizer");
        Date eventDate = resultSet.getDate("eventDate");
        Time eventTime = resultSet.getTime("eventTime");
        String venue = resultSet.getString("venue");
        Integer guestsNo = resultSet.getInt("guestsNo");
        Integer ticketPrice = resultSet.getInt("ticketPrice");
        String type = resultSet.getString("type");
        String status = resultSet.getString("status");
        String eventDetails = resultSet.getString("details");
        Integer availableSeats = resultSet.getInt("availableSeats");
        return new Event(id, name, organizer, eventDate, eventTime, venue, guestsNo, ticketPrice, type, status, eventDetails, availableSeats);
    }

    private Object[] getArgs(UUID id, Event event) {
        return new Object[]{
                id,
                event.getName(),
                event.getOrganizer(),
                event.getEventDate(),
                event.getEventTime(),
                event.getVenue(),
                event.getGuestsNo(),
                event.getTicketPrice(),
                event.getType(),
                event.getStatus(),
                event.getEventDetails(),
                event.getAvailableSeats()
        };
    }

    @Override
    public List<Event> selectAllEvents() {
        final String sql = "SELECT * FROM event";
        return jdbcTemplate.query(sql, EventDataAccessService::mapRow);
    }

    @Override
    public int insertEvent(UUID id, Event event) {
        final String sql = "INSERT INTO event VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        int response = jdbcTemplate.update(sql, getArgs(id, event));
        return response;
    }

    @Override
    public Optional<Event> selectEventById(UUID id) {
        final String sql = "SELECT * FROM event WHERE id = ?";
        Event event = jdbcTemplate.queryForObject(sql, new Object[]{id}, EventDataAccessService::mapRow);
        return Optional.ofNullable(event);
    }

    @Override
    public int updateEvent(UUID id, Event event) {
        final String sql = "UPDATE event SET name=?, organizer=?, eventDate=?, eventTime=?, venue=?, guestsNo=?, ticketPrice=?, type=?, status=?, details=?, availableSeats=? WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{
                event.getName(),
                event.getOrganizer(),
                event.getEventDate(),
                event.getEventTime(),
                event.getVenue(),
                event.getGuestsNo(),
                event.getTicketPrice(),
                event.getType(),
                event.getStatus(),
                event.getEventDetails(),
                event.getAvailableSeats(),
                id});
        return 0;
    }

    @Override
    public Boolean availableSeats(UUID id){
        Event event = this.selectEventById(id).get();
        return event.getAvailableSeats() > 0;
    }


    @Override
    public int addTicket(Ticket ticket, UUID id) {
        Event event = this.selectEventById(id).get();
        event.addTicket(ticket);
        jdbcTemplate.update("UPDATE event SET availableSeats=? WHERE id=?", (event.getAvailableSeats() - 1), id);
        return 0;
    }

    @Override
    public Set<Ticket> getEventTickets(UUID event_id) {
        String sql = "SELECT * FROM ticket WHERE event_id = ?";
        List<Ticket> response = jdbcTemplate.query(sql, new Object[]{event_id}, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String fullName = resultSet.getString("name");
            String type = resultSet.getString("type");
            Integer price = resultSet.getInt("ticketPrice");
            return new Ticket(id, fullName, type, price);

        });
        Set<Ticket> tickets = new HashSet<Ticket>(response);
        return tickets;
    }

}
