package com.example.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Ticket {
    @Id
    private UUID id;
    private String fullName;
    private String type;
    private Integer price;
//    private Event event;

    public Ticket(@JsonProperty("id") UUID id, @JsonProperty("fullName") String fullName, @JsonProperty("type") String type, @JsonProperty("price") Integer price) {
        this.id = id;
        this.fullName = fullName;
        this.type = type;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }
}
