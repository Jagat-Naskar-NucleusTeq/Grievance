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
import com.feedback.entities.Estatus;
import com.feedback.entities.Ticket;
import com.feedback.payloads.ticket_dto.GetTicketsDtoIn;
import com.feedback.payloads.ticket_dto.TicketDto;
import com.feedback.payloads.ticket_dto.UpdateTicketDtoIn;
import com.feedback.payloads.ticket_dto.GetTicketDtoOut;
import com.feedback.service.TicketService;

class TicketControllerTest {

  @InjectMocks
  private TicketController ticketController;
  @Mock
  private TicketService ticketService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

    @Test
    public void testAddTickets() {

      TicketDto ticketDto = new TicketDto();
      ticketDto.setTicketDescription("Sample Description");

      when(ticketService.saveTicket(ticketDto)).thenReturn(new Ticket());

      ResponseEntity<?> responseEntity = ticketController.addTickets(ticketDto);

      assertNotNull(responseEntity);
      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

      assertEquals("Ticket saved!!!", responseEntity.getBody());
    }

    @Test
    public void testAddTicketsWithNullTicket() {
      TicketDto ticketDto = null;
      
      assertThrows(NullPointerFromFrontendException.class, () -> {
        ticketController.addTickets(ticketDto);
      });
    }

    @Test
    void testGetTickets() {
        GetTicketsDtoIn getTicketsDTOin = new GetTicketsDtoIn();
        getTicketsDTOin.setEmail("jme@nucleusteq.com");
        getTicketsDTOin.setDepartmentBased("true");
        getTicketsDTOin.setAssignByOwn("false");
        getTicketsDTOin.setFilterStatus("Open");
        getTicketsDTOin.setPageNumber(1);

        LocalDateTime dummyCreationTime = LocalDateTime.of(2023, 9, 15, 12, 30);
        GetTicketDtoOut dummyTicket = new GetTicketDtoOut(
            1L,
            "Ticket title",
            dummyCreationTime,
            dummyCreationTime,
            Estatus.Open,
            "Feedback",
            "Jagat Naskar",
            "IT Department",
            "Description 1",
            null
        );

        List<GetTicketDtoOut> expectedTicketList = Collections.singletonList(dummyTicket);

        when(ticketService.getTickets(getTicketsDTOin)).thenReturn(expectedTicketList);

        ResponseEntity<?> responseEntity = ticketController.getTickets(getTicketsDTOin);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedTicketList, responseEntity.getBody());
    }

    @Test
    void testGetTickets_NoTicketsFound() {
        // Arrange
        GetTicketsDtoIn getTicketsDTOin = new GetTicketsDtoIn();
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

        UpdateTicketDtoIn updateTicketDtoIn = new UpdateTicketDtoIn(1L, Estatus.Open, comments);

        when(ticketService.updatingTicket(updateTicketDtoIn)).thenReturn(true);

        ResponseEntity<?> responseEntity = ticketController.updateTicket(updateTicketDtoIn);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Ticket Updated.", responseEntity.getBody());
    }

    @Test
    void testUpdateTicket_Failure() {
      ticketController.setTicketService(ticketService);
      
       String comments = "comment1";

       UpdateTicketDtoIn updateTicketDtoIn = new UpdateTicketDtoIn();
       updateTicketDtoIn.setTicketId(1L);
       updateTicketDtoIn.setTicketStatus(Estatus.Open);
       updateTicketDtoIn.setCommentList(comments);

        when(ticketService.updatingTicket(updateTicketDtoIn)).thenReturn(false);

        ResponseEntity<?> responseEntity = ticketController.updateTicket(updateTicketDtoIn);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Could not update your ticket.", responseEntity.getBody());
    }

}
