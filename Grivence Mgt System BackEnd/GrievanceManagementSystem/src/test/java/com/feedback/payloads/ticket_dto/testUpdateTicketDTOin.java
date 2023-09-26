package com.feedback.payloads.ticket_dto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.nullable;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.feedback.entities.Comment;
import com.feedback.entities.EStatus;

public class testUpdateTicketDTOin {

 @Test
    public void testGettersAndSetters() {
        UpdateTicketDTOin dto = new UpdateTicketDTOin();

        dto.setTicketId(1);
        assertEquals(1, dto.getTicketId());

        dto.setTicketStatus(EStatus.Open);
        assertEquals(EStatus.Open, dto.getTicketStatus());

        dto.setCommentList("Test comment");
        assertEquals("Test comment", dto.getComment());
    }

    @Test
    public void testFieldConstructor() {
        UpdateTicketDTOin dto = new UpdateTicketDTOin(1, EStatus.Open, "Test comment");

        assertEquals(1, dto.getTicketId());
        assertEquals(EStatus.Open, dto.getTicketStatus());
        assertEquals("Test comment", dto.getComment());
    }

    @Test
    public void testHashCodeAndEquals() {
        UpdateTicketDTOin dto1 = new UpdateTicketDTOin(1, EStatus.Open, "Test comment");
        UpdateTicketDTOin dto2 = new UpdateTicketDTOin(1, EStatus.Open, "Test comment");

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testToString() {
        UpdateTicketDTOin dto = new UpdateTicketDTOin(1, EStatus.Open, "Test comment");

        String expected = "UpdateTicketDTOin [ticketId=1, ticketStatus=Open]";
        assertEquals(expected, dto.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        UpdateTicketDTOin dto = new UpdateTicketDTOin();

        assertEquals(0, dto.getTicketId());
        assertNull(dto.getTicketStatus());
        assertNull(dto.getComment());
    }

    @Test
    void testToStrings() {
        UpdateTicketDTOin ticketDTO = new UpdateTicketDTOin(1, EStatus.Open, "Sample Comment");
        String expectedString = "UpdateTicketDTOin [ticketId=1, ticketStatus=Open]";
        assertEquals(expectedString, ticketDTO.toString());
    }

    @Test
    void testHashCode() {
        UpdateTicketDTOin ticketDTO1 = new UpdateTicketDTOin(1, EStatus.Open, "Sample Comment");
        UpdateTicketDTOin ticketDTO2 = new UpdateTicketDTOin(1, EStatus.Open, "Sample Comment");

        assertEquals(ticketDTO1.hashCode(), ticketDTO2.hashCode());
    }

    @Test
    void testEquals() {
        UpdateTicketDTOin ticketDTO1 = new UpdateTicketDTOin(1, EStatus.Open, "Sample Comment");
        UpdateTicketDTOin ticketDTO2 = new UpdateTicketDTOin(1, EStatus.Open, "Sample Comment");
        UpdateTicketDTOin ticketDTO3 = new UpdateTicketDTOin(2, EStatus.Resolved, "Another Comment");

        assertEquals(ticketDTO1, ticketDTO2);
        assertNotEquals(ticketDTO1, ticketDTO3);
    }

}
