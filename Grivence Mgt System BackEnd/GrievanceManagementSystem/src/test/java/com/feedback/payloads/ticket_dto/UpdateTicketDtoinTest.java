package com.feedback.payloads.ticket_dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.feedback.entities.Estatus;

public class UpdateTicketDtoinTest {
    @Test
    public void testGettersAndSetters() {
        UpdateTicketDtoIn dto = new UpdateTicketDtoIn();
        dto.setTicketId(1);
        assertEquals(1, dto.getTicketId());
        dto.setTicketStatus(Estatus.Open);
        assertEquals(Estatus.Open, dto.getTicketStatus());
        dto.setCommentList("Test comment");
        assertEquals("Test comment", dto.getComment());
    }

    @Test
    public void testFieldConstructor() {
        UpdateTicketDtoIn dto = new UpdateTicketDtoIn(1, Estatus.Open,
                "Test comment");
        assertEquals(1, dto.getTicketId());
        assertEquals(Estatus.Open, dto.getTicketStatus());
        assertEquals("Test comment", dto.getComment());
    }

    @Test
    public void testHashCodeAndEquals() {
        UpdateTicketDtoIn dto1 = new UpdateTicketDtoIn(1, Estatus.Open,
                "Test comment");
        UpdateTicketDtoIn dto2 = new UpdateTicketDtoIn(1, Estatus.Open,
                "Test comment");
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, new Object());
        
        dto2 = new UpdateTicketDtoIn(2, Estatus.Open,
                "Test comment");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto2);

        dto2 = new UpdateTicketDtoIn(1, Estatus.Resolved,
                "Test comment");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto2);
        
        dto2 = new UpdateTicketDtoIn(1, Estatus.Open,
                "Test");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto2);
    }

    @Test
    public void testToString() {
        UpdateTicketDtoIn dto = new UpdateTicketDtoIn(1, Estatus.Open,
                "Test comment");
        String expected = "UpdateTicketDTOin [ticketId=1, ticketStatus=Open]";
        assertEquals(expected, dto.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        UpdateTicketDtoIn dto = new UpdateTicketDtoIn();
        assertEquals(0, dto.getTicketId());
        assertNull(dto.getTicketStatus());
        assertNull(dto.getComment());
    }

    @Test
    void testToStrings() {
        UpdateTicketDtoIn ticketDTO = new UpdateTicketDtoIn(1, Estatus.Open,
                "Comment 1");
        String expectedString = "UpdateTicketDTOin [ticketId=1, ticketStatus=Open]";
        assertEquals(expectedString, ticketDTO.toString());
    }

}
