CREATE TABLE event (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    organizer VARCHAR(100),
    eventDate DATE,
    eventTime TIME,
    venue VARCHAR(200),
    guestsNo INTEGER,
    ticketPrice INTEGER

);
