package com.feedback.payloads.ticket_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.feedback.entities.EStatus;
import com.feedback.entities.Ticket;

class TicketDtoTest {

	 @Test
  void testEquals() {
      TicketDto ticket1 = new TicketDto(1L, "Title", "Type", EStatus.Open, "Description", "email@example.com", "IT");
      TicketDto ticket2 = new TicketDto(1L, "Title", "Type", EStatus.Open, "Description", "email@example.com", "IT");
      TicketDto ticket3 = new TicketDto(2L, "Title", "Type", EStatus.Open, "Description", "email@example.com", "IT");

      assertEquals(ticket1, ticket2);
      assertNotEquals(ticket1, ticket3);
  }

  @Test
  void testHashCode() {
      TicketDto ticket1 = new TicketDto(1L, "Title", "Type", EStatus.Open, "Description", "email@example.com", "IT");
      TicketDto ticket2 = new TicketDto(1L, "Title", "Type", EStatus.Open, "Description", "email@example.com", "IT");

      assertEquals(ticket1.hashCode(), ticket2.hashCode());
  }

  @Test
  void testToString() {
      TicketDto ticket = new TicketDto(1L, "Title", "Type", EStatus.Open, "Description", "email@example.com", "IT");
      String expected = "NewTicketDTO [ticketId=1, ticketTitle=Title, ticketType=Type, ticketStatus=Open, " +
              "ticketDescription=Description, senderEmail=email@example.com, deptName=IT]";

      assertEquals(expected, ticket.toString());
  }
  
  @Test
  public void testGetTicketId() {
	  Ticket ticket = new Ticket();
      Long expected = 123L;
      ticket.setTicketId(expected);
      Long actual = ticket.getTicketId();

      assertEquals(expected, actual);
  }

  @Test
  public void testSetTicketId() {
	  Ticket ticket = new Ticket();
      Long expected = 456L;
      ticket.setTicketId(expected);
      Long actual = ticket.getTicketId();

      assertEquals(expected, actual);
  }

  @Test
  void testEqualss() {
      TicketDto dto1 = new TicketDto(1L, "Title", "Type", EStatus.Open, "Description", "email@example.com", "DeptA");
      TicketDto dto2 = new TicketDto(1L, "Title", "Type", EStatus.Open, "Description", "email@example.com", "DeptA");
      TicketDto differentDto = new TicketDto(2L, "Different Title", "Different Type", EStatus.Resolved, "Different Description", "email2@example.com", "DeptB");

      assertTrue(dto1.equals(dto1));

      assertFalse(dto1.equals(null));
      assertFalse(dto1.equals("This is a string"));

      assertFalse(dto1.equals(differentDto));
      assertTrue(dto1.equals(dto2));
  }
}
