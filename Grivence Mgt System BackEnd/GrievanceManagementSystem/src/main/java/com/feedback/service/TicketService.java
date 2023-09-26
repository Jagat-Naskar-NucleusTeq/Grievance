package com.feedback.service;

import com.feedback.entities.Ticket;
import com.feedback.payloads.ticket_dto.GetTicketsDTOin;
import com.feedback.payloads.ticket_dto.NewTicketDTO;
import com.feedback.payloads.ticket_dto.UpdateTicketDTOin;
import com.feedback.payloads.ticket_dto.getTicketDTOout;
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
  Ticket saveTicket(NewTicketDTO ticket);

  /**
   * Get a list of tickets based on the provided criteria.
   *
   * @param getTicketsDTOin The criteria for retrieving tickets.
   *
   * @return A list of ticket information.
   */
  List<getTicketDTOout> getTickets(GetTicketsDTOin getTicketsDTOin);

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
  getTicketDTOout getByTicketById(Long ticketId);
}
