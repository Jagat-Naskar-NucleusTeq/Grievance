package com.feedback.payloads.ticket_dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.feedback.entities.EStatus;

public class UpdateTicketDTOinTest {
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
        UpdateTicketDTOin dto = new UpdateTicketDTOin(1, EStatus.Open,
                "Test comment");
        assertEquals(1, dto.getTicketId());
        assertEquals(EStatus.Open, dto.getTicketStatus());
        assertEquals("Test comment", dto.getComment());
    }

    @Test
    public void testHashCodeAndEquals() {
        UpdateTicketDTOin dto1 = new UpdateTicketDTOin(1, EStatus.Open,
                "Test comment");
        UpdateTicketDTOin dto2 = new UpdateTicketDTOin(1, EStatus.Open,
                "Test comment");
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, new Object());
        
        dto2 = new UpdateTicketDTOin(2, EStatus.Open,
                "Test comment");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto2);

        dto2 = new UpdateTicketDTOin(1, EStatus.Resolved,
                "Test comment");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto2);
        
        dto2 = new UpdateTicketDTOin(1, EStatus.Open,
                "Test");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto2);
    }

    @Test
    public void testToString() {
        UpdateTicketDTOin dto = new UpdateTicketDTOin(1, EStatus.Open,
                "Test comment");
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
        UpdateTicketDTOin ticketDTO = new UpdateTicketDTOin(1, EStatus.Open,
                "Comment 1");
        String expectedString = "UpdateTicketDTOin [ticketId=1, ticketStatus=Open]";
        assertEquals(expectedString, ticketDTO.toString());
    }

}
