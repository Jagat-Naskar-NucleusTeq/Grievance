package com.feedback.payloads.ticket_dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.feedback.entities.EStatus;
import com.feedback.payloads.comment_dto.getCommentDTOout;

class GetTicketDTOoutTest {

	@Test
    void testGettersAndSetters() {
        LocalDateTime now = LocalDateTime.now();

        List<getCommentDTOout> comments = new ArrayList<>();
        comments.add(new getCommentDTOout());
        comments.add(new getCommentDTOout());

        getTicketDTOout dto = new getTicketDTOout(1L, "Title", now, now,
                EStatus.Open, "Type", "Creator", "Description", "Department", comments);

        assertEquals(1L, dto.getTicketId());
        assertEquals("Title", dto.getTitle());
        assertEquals(now, dto.getCreationTime());
        assertEquals(now, dto.getUpdationTime());
        assertEquals(EStatus.Open, dto.getTicketStatus());
        assertEquals("Type", dto.getTicketType());
        assertEquals("Creator", dto.getCreatedBy());
        assertEquals("Department", dto.getDepartmentName());
        assertEquals("Description", dto.getTicketDescription());
        assertEquals(comments, dto.getComments());

        LocalDateTime newTime = now.plusHours(1);
        List<getCommentDTOout> newComments = new ArrayList<>();
        newComments.add(new getCommentDTOout());

        dto.setTicketId(2L);
        dto.setTitle("New Title");
        dto.setCreationTime(newTime);
        dto.setUpdationTime(newTime);
        dto.setTicketStatus(EStatus.Being_Addressed);
        dto.setTicketType("New Type");
        dto.setCreatedBy("New Creator");
        dto.setDepartmentName("New Department");
        dto.setTicketDescription("New Description");
        dto.setComments(newComments);

        assertEquals(2L, dto.getTicketId());
        assertEquals("New Title", dto.getTitle());
        assertEquals(newTime, dto.getCreationTime());
        assertEquals(newTime, dto.getUpdationTime());
        assertEquals(EStatus.Being_Addressed, dto.getTicketStatus());
        assertEquals("New Type", dto.getTicketType());
        assertEquals("New Creator", dto.getCreatedBy());
        assertEquals("New Department", dto.getDepartmentName());
        assertEquals("New Description", dto.getTicketDescription());
        assertEquals(newComments, dto.getComments());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime dummyDateTime = LocalDateTime.of(2023, 9, 13, 12, 30);
        getTicketDTOout dto1 = new getTicketDTOout(1L, "Title", dummyDateTime,
                dummyDateTime, EStatus.Open, "Type", "Creator", "Department", "Description", new ArrayList<>());

        getTicketDTOout dto2 = new getTicketDTOout(1L, "Title", dummyDateTime,
                dummyDateTime, EStatus.Open, "Type", "Creator", "Department", "Description", new ArrayList<>());

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());

        dto2.setTitle("New Title");
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testToString() {
        getCommentDTOout commentDTO = new getCommentDTOout("Jagat", "This is a comment", 1);
        String expected = "getCommentDTOout [commentedByUser=Jagat, commentMessage=This is a comment, commentId=1]";
        assertEquals(expected, commentDTO.toString());
    }

    @Test
    void testEquals() {
        getCommentDTOout commentDTO1 = new getCommentDTOout("Jagat", "This is a comment", 1);
        getCommentDTOout commentDTO2 = new getCommentDTOout("Jagat", "This is a comment", 1);
        getCommentDTOout differentCommentDTO = new getCommentDTOout("JagatNaskar", "This is a different comment", 2);

        assertTrue(commentDTO1.equals(commentDTO1));

        assertFalse(commentDTO1.equals(null));
        assertFalse(commentDTO1.equals("This is a string"));

        assertFalse(commentDTO1.equals(differentCommentDTO));
        assertTrue(commentDTO1.equals(commentDTO2));
    }
}
