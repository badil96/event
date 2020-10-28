CREATE TABLE ticket (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    event_id UUID,
    ticketPrice INTEGER,
    type VARCHAR(100),
    constraint fk_ticket_event_id foreign key (event_id) references event (id)
);