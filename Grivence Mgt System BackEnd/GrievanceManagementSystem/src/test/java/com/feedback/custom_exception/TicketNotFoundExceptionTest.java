package com.feedback.custom_exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicketNotFoundExceptionTest {


    @Test
    public void testMessageConstructor() {
        TicketNotFoundException exception = new TicketNotFoundException("Ticket not found!");

        assertEquals("Ticket not found!", exception.getMessage());
    }

    @Test
    public void testIdConstructor() {
        TicketNotFoundException exception = new TicketNotFoundException(123);

        assertEquals("Ticket not found with id: 123", exception.getMessage());
    }

}
