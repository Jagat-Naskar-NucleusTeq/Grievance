package com.feedback.payloads.ticket_dto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.ArgumentMatchers.nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.feedback.entities.Estatus;
import com.feedback.payloads.comment_dto.GetCommentDtoOut;

class GetTicketDTOoutTest {

	@Test
    void testGettersAndSetters() {
        LocalDateTime now = LocalDateTime.now();

        List<GetCommentDtoOut> comments = new ArrayList<>();
        comments.add(new GetCommentDtoOut());
        comments.add(new GetCommentDtoOut());

        GetTicketDtoOut dto = new GetTicketDtoOut(1L, "Title", now, now,
                Estatus.Open, "Type", "Creator", "Description", "Department", comments);

        assertEquals(1L, dto.getTicketId());
        assertEquals("Title", dto.getTitle());
        assertEquals(now, dto.getCreationTime());
        assertEquals(now, dto.getUpdationTime());
        assertEquals(Estatus.Open, dto.getTicketStatus());
        assertEquals("Type", dto.getTicketType());
        assertEquals("Creator", dto.getCreatedBy());
        assertEquals("Department", dto.getDepartmentName());
        assertEquals("Description", dto.getTicketDescription());
        assertEquals(comments, dto.getComments());

        LocalDateTime newTime = now.plusHours(1);
        List<GetCommentDtoOut> newComments = new ArrayList<>();
        newComments.add(new GetCommentDtoOut());

        dto.setTicketId(2L);
        dto.setTitle("New Title");
        dto.setCreationTime(newTime);
        dto.setUpdationTime(newTime);
        dto.setTicketStatus(Estatus.Being_Addressed);
        dto.setTicketType("New Type");
        dto.setCreatedBy("New Creator");
        dto.setDepartmentName("New Department");
        dto.setTicketDescription("New Description");
        dto.setComments(newComments);

        assertEquals(2L, dto.getTicketId());
        assertEquals("New Title", dto.getTitle());
        assertEquals(newTime, dto.getCreationTime());
        assertEquals(newTime, dto.getUpdationTime());
        assertEquals(Estatus.Being_Addressed, dto.getTicketStatus());
        assertEquals("New Type", dto.getTicketType());
        assertEquals("New Creator", dto.getCreatedBy());
        assertEquals("New Department", dto.getDepartmentName());
        assertEquals("New Description", dto.getTicketDescription());
        assertEquals(newComments, dto.getComments());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime dummyDateTime = LocalDateTime.of(2023, 9, 13, 12, 30);
        GetTicketDtoOut dto1 = new GetTicketDtoOut(1L, "Title", dummyDateTime,
                dummyDateTime, Estatus.Open, "Type", "Creator", "Department", "Description", new ArrayList<>());

        GetTicketDtoOut dto2 = new GetTicketDtoOut(1L, "Title", dummyDateTime,
                dummyDateTime, Estatus.Open, "Type", "Creator", "Department", "Description", new ArrayList<>());

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());

        dto2.setTitle("New Title");
        assertNotEquals(dto1, dto2);
    }

    @Test
    public void testToString() {
        List<GetCommentDtoOut> comments = new ArrayList<>();
        comments.add(new GetCommentDtoOut("jme@nucleustreq.com", "Comment 1", 1));

        GetTicketDtoOut ticket = new GetTicketDtoOut(
                1L, "Title", LocalDateTime.now(), LocalDateTime.now(),
                Estatus.Open, "Type", "User", "Description",
                "Department", comments);

        String expectedToString = "GetTicketDtoOut [ticketId=1, title=Title, creationTime=" + ticket.getCreationTime() +
                ", updationTime=" + ticket.getUpdationTime() +
                ", ticketStatus=Open, ticketType=Type, createdBy=User, " +
                "departmentName=Department, ticketDescription=Description, comments=" + comments + "]";

        assertEquals(expectedToString, ticket.toString());
    }

    @Test
    public void testEquals() {
        List<GetCommentDtoOut> comments1 = new ArrayList<>();
        comments1.add(new GetCommentDtoOut("jme@nucleustreq.com", "Comment 1", 1));

        List<GetCommentDtoOut> comments2 = new ArrayList<>();
        comments2.add(new GetCommentDtoOut("jme@nucleustreq.com", "Comment 2", 2));
        LocalDateTime dummyDateTime = LocalDateTime.of(2022, 10, 3, 15, 30);
        
        GetTicketDtoOut ticket1 = new GetTicketDtoOut(
                1L, "Title", dummyDateTime, dummyDateTime,
                Estatus.Open, "Type", "User", "Description",
                "Department", comments1);

        GetTicketDtoOut ticket2 = new GetTicketDtoOut(
                1L, "Title", dummyDateTime, dummyDateTime,
                Estatus.Open, "Type", "User", "Description",
                "Department", comments1);

        GetTicketDtoOut ticket3 = new GetTicketDtoOut(
                2L, "Title", dummyDateTime, LocalDateTime.now(),
                Estatus.Open, "Type", "User", "Description",
                "Department", comments2);

        GetCommentDtoOut ticket4CommentDtoOut = new GetCommentDtoOut();
        boolean bullObject = ticket4CommentDtoOut.equals(null);

        assertTrue(ticket1.equals(ticket2));
        assertFalse(ticket1.equals(ticket3));
        assertFalse(bullObject);
    }
}
