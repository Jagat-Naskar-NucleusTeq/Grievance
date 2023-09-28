package com.feedback.service;

import com.feedback.entities.Ticket;
import com.feedback.payloads.ticket_dto.GetTicketsDtoIn;
import com.feedback.payloads.ticket_dto.TicketDto;
import com.feedback.payloads.ticket_dto.UpdateTicketDTOin;
import com.feedback.payloads.ticket_dto.GetTicketDtoOut;
import java.util.List;

/**
 * Service interface for managing tickets.
 */
public interface TicketService {

  /**
   * save ticket in the database.
   *
   * @param ticket
   *
   * @return Ticket
   */
  Ticket saveTicket(TicketDto ticket);

  /**
   * Get a list of tickets based on the provided criteria.
   *
   * @param getTicketsDTOin The criteria for retrieving tickets.
   *
   * @return A list of ticket information.
   */
  List<GetTicketDtoOut> getTickets(GetTicketsDtoIn getTicketsDTOin);

  /**
   * Update a ticket with the provided information.
   *
   * @param updateTicketDTOin update the ticket.
   *
   * @return True if the update is successful, otherwise false.
   */
  Boolean updatingTicket(UpdateTicketDTOin updateTicketDTOin);

  /**
   * Get a ticket by its ID.
   *
   * @param ticketId The ID of the ticket to retrieve.
   *
   * @return The ticket information.
   */
  GetTicketDtoOut getByTicketById(Long ticketId);
}
