package com.feedback.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.feedback.custom_exception.NullPointerFromFrontendException;
import com.feedback.entities.EStatus;
import com.feedback.entities.Ticket;
import com.feedback.payloads.ticket_dto.GetTicketsDTOin;
import com.feedback.payloads.ticket_dto.NewTicketDTO;
import com.feedback.payloads.ticket_dto.UpdateTicketDTOin;
import com.feedback.payloads.ticket_dto.getTicketDTOout;
import com.feedback.service.TicketService;

class TicketControllerTest {

@InjectMocks
private TicketController ticketController;
@Mock
private TicketService ticketService;

@BeforeEach
void setUp() {
	MockitoAnnotations.initMocks(this);
//    ticketService = Mockito.mock(TicketService.class);
//    ticketController = new TicketController();
}

    @Test
    public void testAddTickets() {
//      ticketController.ticketService = ticketService;

      NewTicketDTO newTicketDTO = new NewTicketDTO();
      newTicketDTO.setTicketDescription("Sample Description");

      when(ticketService.saveTicket(newTicketDTO)).thenReturn(new Ticket());

      ResponseEntity<?> responseEntity = ticketController.addTickets(newTicketDTO);

      assertNotNull(responseEntity);
      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

      assertEquals("Ticket saved!!!", responseEntity.getBody());
    }

    @Test
    public void testAddTicketsWithNullTicket() {
      NewTicketDTO newTicketDTO = null;
      
      assertThrows(NullPointerFromFrontendException.class, () -> {
        ticketController.addTickets(newTicketDTO);
      });
    }

    @Test
    void testGetTickets() {
        GetTicketsDTOin getTicketsDTOin = new GetTicketsDTOin();
        getTicketsDTOin.setEmail("jme@nucleusteq.com");
        getTicketsDTOin.setDepartmentBased("true");
        getTicketsDTOin.setAssignByOwn("false");
        getTicketsDTOin.setFilterStatus("Open");
        getTicketsDTOin.setPageNumber(1);

        LocalDateTime dummyCreationTime = LocalDateTime.of(2023, 9, 15, 12, 30);
        getTicketDTOout dummyTicket = new getTicketDTOout(
            1L,
            "Ticket title",
            dummyCreationTime,
            dummyCreationTime,
            EStatus.Open,
            "Feedback",
            "Jagat Naskar",
            "IT Department",
            "Description 1",
            null
        );

        List<getTicketDTOout> expectedTicketList = Collections.singletonList(dummyTicket);

        when(ticketService.getTickets(getTicketsDTOin)).thenReturn(expectedTicketList);

        ResponseEntity<?> responseEntity = ticketController.getTickets(getTicketsDTOin);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedTicketList, responseEntity.getBody());
    }

    @Test
    void testGetTickets_NoTicketsFound() {
        // Arrange
        GetTicketsDTOin getTicketsDTOin = new GetTicketsDTOin();
        getTicketsDTOin.setEmail("jme@nucleusteq.com");
        getTicketsDTOin.setDepartmentBased("true");
        getTicketsDTOin.setAssignByOwn("false");
        getTicketsDTOin.setFilterStatus("Open");
        getTicketsDTOin.setPageNumber(1);

        when(ticketService.getTickets(getTicketsDTOin)).thenReturn(new ArrayList<>());

        ResponseEntity<?> responseEntity = ticketController.getTickets(getTicketsDTOin);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(((List<?>) responseEntity.getBody()).isEmpty());
    }

    @Test
    void testUpdateTicket_Success() {
      ticketController.setTicketService(ticketService);
      String comments = "message1";

        UpdateTicketDTOin updateTicketDTOin = new UpdateTicketDTOin(1L, EStatus.Open, comments);

        when(ticketService.updatingTicket(updateTicketDTOin)).thenReturn(true);

        ResponseEntity<?> responseEntity = ticketController.updateTicket(updateTicketDTOin);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Ticket Updated.", responseEntity.getBody());
    }

    @Test
    void testUpdateTicket_Failure() {
      ticketController.setTicketService(ticketService);
      
       String comments = "comment1";

       UpdateTicketDTOin updateTicketDTOin = new UpdateTicketDTOin();
       updateTicketDTOin.setTicketId(1L);
       updateTicketDTOin.setTicketStatus(EStatus.Open);
       updateTicketDTOin.setCommentList(comments);

        when(ticketService.updatingTicket(updateTicketDTOin)).thenReturn(false);

        ResponseEntity<?> responseEntity = ticketController.updateTicket(updateTicketDTOin);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Could not update your ticket.", responseEntity.getBody());
    }

}
