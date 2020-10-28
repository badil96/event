package com.example.event.dao;

import com.example.event.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDao")
public class TicketDataAccessService implements TicketDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ticket> selectAllTickets() {
        final String sql = "SELECT * FROM ticket";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String fullName = resultSet.getString("name");
            String type = resultSet.getString("type");
            Integer price = resultSet.getInt("ticketPrice");
            return new Ticket(id, fullName, type, price);
        });
    }

    @Override
    public UUID insertTicket(UUID id, UUID eventId, Ticket ticket) {
        final String sql = "INSERT INTO ticket VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{id, ticket.getFullName(), eventId,ticket.getPrice(),ticket.getType()});
        return id;
    }

    @Override
    public Optional<Ticket> selectTicketById(UUID id) {
        final String sql = "SELECT * FROM ticket WHERE id = ?";
        Ticket ticket = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            String fullName = resultSet.getString("name");
            String type = resultSet.getString("type");
            Integer price = resultSet.getInt("ticketPrice");
            return new Ticket(id, fullName, type, price);
        });
        return Optional.ofNullable(ticket);
    }
}
