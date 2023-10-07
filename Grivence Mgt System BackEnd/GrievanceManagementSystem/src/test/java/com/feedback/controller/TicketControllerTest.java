package com.feedback.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.payloads.ticket_dto.TicketDto;
import com.feedback.payloads.ticket_dto.UpdateTicketDtoIn;
import com.feedback.entities.Estatus;
import com.feedback.entities.Ticket;
import com.feedback.payloads.ticket_dto.GetTicketsDtoIn;
import com.feedback.payloads.ticket_dto.GetTicketDtoOut;
import com.feedback.service.TicketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TicketControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketService ticketService;


    @InjectMocks
    TicketController ticketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    public void testAddTickets() throws Exception {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setTicketDescription("Sample Description");

        when(ticketService.saveTicket(any(TicketDto.class))).thenReturn(new Ticket());

        mockMvc.perform(post("/api/tickets/addTicket")
                .contentType("application/json")
                .content(asJsonString(ticketDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Ticket saved!!!"));
    }

    @Test
    public void testAddTicketsWithNullTicket() throws Exception {
        mockMvc.perform(post("/api/tickets/addTicket"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetTickets() throws Exception {
        GetTicketsDtoIn getTicketsDTOin = new GetTicketsDtoIn();
        getTicketsDTOin.setEmail("jme@nucleusteq.com");
        getTicketsDTOin.setDepartmentBased("true");
        getTicketsDTOin.setAssignByOwn("false");
        getTicketsDTOin.setFilterStatus("Open");
        getTicketsDTOin.setPageNumber(1);

//        LocalDateTime dummyCreationTime = LocalDateTime.of(2023, 9, 15, 12, 30);
        GetTicketDtoOut dummyTicket = new GetTicketDtoOut(
                1L,
                "Ticket title",
                null,
                null,
                Estatus.Open,
                "Feedback",
                "Jagat Naskar",
                "IT Department",
                "Description 1",
                null
        );

        List<GetTicketDtoOut> expectedTicketList = Collections.singletonList(dummyTicket);

        when(ticketService.getTickets(getTicketsDTOin)).thenReturn(expectedTicketList);

        mockMvc.perform(post("/api/tickets/getAllTicket")
                .contentType("application/json")
                .content(asJsonString(getTicketsDTOin)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedTicketList)));
    }

    @Test
    void testGetTickets_NoTicketsFound() throws Exception {
        GetTicketsDtoIn getTicketsDTOin = new GetTicketsDtoIn();
        getTicketsDTOin.setEmail("jme@nucleusteq.com");
        getTicketsDTOin.setDepartmentBased("true");
        getTicketsDTOin.setAssignByOwn("false");
        getTicketsDTOin.setFilterStatus("Open");
        getTicketsDTOin.setPageNumber(1);

        when(ticketService.getTickets(getTicketsDTOin)).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/api/tickets/getAllTicket")
                .contentType("application/json")
                .content(asJsonString(getTicketsDTOin)))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testUpdateTicket_Success() throws Exception {
        String comments = "message1";

        UpdateTicketDtoIn updateTicketDtoIn = new UpdateTicketDtoIn(1L, Estatus.Open, comments);

        when(ticketService.updatingTicket(updateTicketDtoIn)).thenReturn(true);

        mockMvc.perform(post("/api/tickets/updateTicket")
                .contentType("application/json")
                .content(asJsonString(updateTicketDtoIn)))
                .andExpect(status().isOk())
                .andExpect(content().string("Ticket Updated."));
    }

    @Test
    void testUpdateTicket_Failure() throws Exception {
        String comments = "comment1";

        UpdateTicketDtoIn updateTicketDtoIn = new UpdateTicketDtoIn();
       updateTicketDtoIn.setTicketId(1L);
        updateTicketDtoIn.setTicketStatus(Estatus.Open);
        updateTicketDtoIn.setCommentList(comments);

        when(ticketService.updatingTicket(updateTicketDtoIn)).thenReturn(false);

        mockMvc.perform(post("/api/tickets/updateTicket")
                .contentType("application/json")
                .content(asJsonString(updateTicketDtoIn)))
                .andExpect(status().isOk())
                .andExpect(content().string("Could not update your ticket."));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}