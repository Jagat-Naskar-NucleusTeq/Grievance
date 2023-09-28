package com.feedback.payloads.ticket_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GetTicketDTOinTest {
	@Test
    public void testGettersAndSetters() {
        GetTicketsDtoIn dto = new GetTicketsDtoIn();

        dto.setEmail("jme@nucleusteq.com");
        assertEquals("jme@nucleusteq.com", dto.getEmail());

        dto.setDepartmentBased("IT");
        assertEquals("IT", dto.getDepartmentBased());

        dto.setAssignByOwn("user123");
        assertEquals("user123", dto.getAssignByOwn());

        dto.setFilterStatus("Open");
        assertEquals("Open", dto.getFilterStatus());

        dto.setPageNumber(1);
        assertEquals(1, dto.getPageNumber());
    }

    @Test
    public void testFieldConstructor() {
        GetTicketsDtoIn dto = new GetTicketsDtoIn("jme@nucleusteq.com", "IT", "user123", "Open", 1);

        assertEquals("jme@nucleusteq.com", dto.getEmail());
        assertEquals("IT", dto.getDepartmentBased());
        assertEquals("user123", dto.getAssignByOwn());
        assertEquals("Open", dto.getFilterStatus());
        assertEquals(1, dto.getPageNumber());
    }

    @Test
    public void testHashCodeAndEquals() {
        GetTicketsDtoIn dto1 = new GetTicketsDtoIn("jme@nucleusteq.com", "IT", "user123", "Open", 1);
        GetTicketsDtoIn dto2 = new GetTicketsDtoIn("jme@nucleusteq.com", "IT", "user123", "Open", 1);

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testToString() {
        GetTicketsDtoIn dto = new GetTicketsDtoIn("jme@nucleusteq.com", "IT", "user123", "Open", 1);

        String expected = "GetTicketsDtoIn [email=jme@nucleusteq.com, departmentBased=IT, assignByOwn=user123, filterStatus=Open, pageNumber=1]";
        assertEquals(expected, dto.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        GetTicketsDtoIn dto = new GetTicketsDtoIn();

        assertNull(dto.getEmail());
        assertNull(dto.getDepartmentBased());
        assertNull(dto.getAssignByOwn());
        assertNull(dto.getFilterStatus());
        assertEquals(0, dto.getPageNumber());
    }

    @Test
    void testEquals() {
        GetTicketsDtoIn dto1 = new GetTicketsDtoIn("jme@nucleusteq.com", "HR", "user1", "Open", 1);
        GetTicketsDtoIn dto2 = new GetTicketsDtoIn("jme@nucleusteq.com", "HR", "user1", "Open", 1);
        GetTicketsDtoIn differentDto = new GetTicketsDtoIn("jme222@nucleusteq.com", "Tech", "UserY", "Resolved", 2);

        assertTrue(dto1.equals(dto1)); 

        assertFalse(dto1.equals(null)); 
        assertFalse(dto1.equals("This is a string"));

        assertFalse(dto1.equals(differentDto)); 
        assertTrue(dto1.equals(dto2)); 
    }

}
