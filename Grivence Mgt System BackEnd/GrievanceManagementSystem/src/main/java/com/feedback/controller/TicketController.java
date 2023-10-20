package com.feedback.controller;

import com.feedback.custom_exception.NullPointerFromFrontendException;
import com.feedback.entities.Ticket;
import com.feedback.payloads.ticket_dto.GetTicketsDtoIn;
import com.feedback.payloads.ticket_dto.TicketDto;
import com.feedback.payloads.ticket_dto.UpdateTicketDtoIn;
import com.feedback.payloads.ticket_dto.GetTicketDtoOut;
import com.feedback.service.TicketService;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ticket Controller class.
 *
 * @author jagat.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/tickets")
public class TicketController {
  /**
   * ticket service object initialization.
   */
  @Autowired
  private TicketService ticketService;

  /**
   * Logger initialization.
   */
  private static final Logger LOGGER = LogManager
        .getLogger(TicketController.class);

  /**
   * adding tickets to databases.
   *
   * @param ticket
   *
   * @return saved ticket string.
   */
  @PostMapping("/addTicket")
  public ResponseEntity<?> addTickets(@RequestBody final TicketDto ticket) {
      LOGGER.info("____Add Ticket Controller_____");

    if (ticket == null) {
      LOGGER.error("Not got data from frontend");
      throw new NullPointerFromFrontendException(
        "Ticket data not received in the backend."
      );
    }

    String message = "";
    Ticket savedTicket = ticketService.saveTicket(ticket);
    if (savedTicket != null) {
      message = "Ticket saved Successfully!!!";
    }
    if (savedTicket == null) {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    return ResponseEntity.status(HttpStatus.OK).body("Ticket saved!!!");
  }

  /**
   * getting tickets based on certain condition.
   *
   * @param getTicketsDTOin
   *
   * @return list of ticket.
   */
  @PostMapping("/getAllTicket")
  public ResponseEntity<?> getTickets(
      @Valid @RequestBody final GetTicketsDtoIn getTicketsDTOin
  ) {
    LOGGER.info("________get ticket controller________");
    List<GetTicketDtoOut> allTicketList = ticketService
        .getTickets(getTicketsDTOin);
    return ResponseEntity.status(HttpStatus.OK).body(allTicketList);
  }

  /**
   * updating status and comment on the ticket.
   *
   * @param updateTicketDtoIn
   *
   * @return updateTicket.
   */
  @PostMapping("/updateTicket")
  public ResponseEntity<?> updateTicket(
      @Valid @RequestBody final UpdateTicketDtoIn updateTicketDtoIn
  ) {
    LOGGER.info("_____update tickrt controller________");
    Boolean updatedTicket = ticketService.updatingTicket(updateTicketDtoIn);
    if (updatedTicket) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body("Ticket Updated.");
    }
    return ResponseEntity
        .status(HttpStatus.OK)
        .body("Could not update your ticket.");
  }

  /**
   * set it to work in the test cases.
   *
   * @param ticketServicee
   *
   */
  public void setTicketService(final TicketService ticketServicee) {
    this.ticketService = ticketServicee;
  }

  /**
   * Get ticket by its id.
   *
   * @param ticketId
   *
   * @return string.
   */
  @GetMapping("/getIcketById/{ticketId}")
  public ResponseEntity<?> getTicketById(
      @PathVariable("ticketId") final Long ticketId) {
    LOGGER.info("_____get ticket(id) controller________");
    GetTicketDtoOut ticketDTOout = ticketService.getByTicketById(ticketId);
    if (ticketDTOout != null) {
      return ResponseEntity.status(HttpStatus.OK).body(ticketDTOout);
    }
    return ResponseEntity.status(HttpStatus.OK).body("Tickets not found");
  }
}
