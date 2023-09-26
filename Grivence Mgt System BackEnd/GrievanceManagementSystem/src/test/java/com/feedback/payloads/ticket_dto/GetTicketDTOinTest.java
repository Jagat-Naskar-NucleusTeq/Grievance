package com.feedback.payloads.ticket_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GetTicketDTOinTest {
	@Test
    public void testGettersAndSetters() {
        GetTicketsDTOin dto = new GetTicketsDTOin();

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
        GetTicketsDTOin dto = new GetTicketsDTOin("jme@nucleusteq.com", "IT", "user123", "Open", 1);

        assertEquals("jme@nucleusteq.com", dto.getEmail());
        assertEquals("IT", dto.getDepartmentBased());
        assertEquals("user123", dto.getAssignByOwn());
        assertEquals("Open", dto.getFilterStatus());
        assertEquals(1, dto.getPageNumber());
    }

    @Test
    public void testHashCodeAndEquals() {
        GetTicketsDTOin dto1 = new GetTicketsDTOin("jme@nucleusteq.com", "IT", "user123", "Open", 1);
        GetTicketsDTOin dto2 = new GetTicketsDTOin("jme@nucleusteq.com", "IT", "user123", "Open", 1);

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testToString() {
        GetTicketsDTOin dto = new GetTicketsDTOin("jme@nucleusteq.com", "IT", "user123", "Open", 1);

        String expected = "GetTicketsDTOin [email=jme@nucleusteq.com, departmentBased=IT, assignByOwn=user123, filterStatus=Open, pageNumber=1]";
        assertEquals(expected, dto.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        GetTicketsDTOin dto = new GetTicketsDTOin();

        assertNull(dto.getEmail());
        assertNull(dto.getDepartmentBased());
        assertNull(dto.getAssignByOwn());
        assertNull(dto.getFilterStatus());
        assertEquals(0, dto.getPageNumber());
    }

    @Test
    void testEquals() {
        GetTicketsDTOin dto1 = new GetTicketsDTOin("jme@nucleusteq.com", "HR", "user1", "Open", 1);
        GetTicketsDTOin dto2 = new GetTicketsDTOin("jme@nucleusteq.com", "HR", "user1", "Open", 1);
        GetTicketsDTOin differentDto = new GetTicketsDTOin("jme222@nucleusteq.com", "Tech", "UserY", "Resolved", 2);

        assertTrue(dto1.equals(dto1)); 

        assertFalse(dto1.equals(null)); 
        assertFalse(dto1.equals("This is a string"));

        assertFalse(dto1.equals(differentDto)); 
        assertTrue(dto1.equals(dto2)); 
    }

}
