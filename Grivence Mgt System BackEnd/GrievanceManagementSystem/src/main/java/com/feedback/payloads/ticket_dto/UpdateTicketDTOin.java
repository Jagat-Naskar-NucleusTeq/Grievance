package com.feedback.payloads.ticket_dto;

import java.util.Objects;

import com.feedback.entities.EStatus;

/**
 * DTO class for updating a ticket.
 */
public class UpdateTicketDTOin {
  /**
  * The ID of the ticket.
  */
  private long ticketId;

  /**
  * The status of the ticket.
  */
  private EStatus ticketStatus;

  /**
  * The comment for the ticket.
  */
  private String comment;

  /**
  * Getter for ticket ID.
  *
  * @return The ticket ID.
  */
  public long getTicketId() {
    return ticketId;
  }

  /**
  * Setter for ticket ID.
  *
  * @param ticketIdd The ticket ID to be set.
  */
  public void setTicketId(final long ticketIdd) {
    this.ticketId = ticketIdd;
  }

  /**
  * Getter for ticket status.
  *
  * @return ticketStatus
  */
  public EStatus getTicketStatus() {
    return ticketStatus;
  }

  /**
  * Setter for ticket status.
  *
  * @param ticketStatuss
  *
  */
  public void setTicketStatus(final EStatus ticketStatuss) {
    this.ticketStatus = ticketStatuss;
  }

  /**
  * Getter for comment.
  *
  * @return The comment.
  */
  public String getComment() {
    return comment;
  }

  /**
  * Setter for comment.
  *
  * @param commentt
  *
  */
  public void setCommentList(final String commentt) {
    this.comment = commentt;
  }

  /**
  * Override of toString method.
  */
  @Override
  public String toString() {
    return "UpdateTicketDTOin [ticketId=" + ticketId
      + ", ticketStatus=" + ticketStatus
      + "]";
  }

  /**
  * Constructor with parameters.
  *
  * @param ticketIdd The ID of the ticket.
  *
  * @param ticketStatuss The status of the ticket.
  *
  * @param commentt The comment for the ticket.
  *
  */
  public UpdateTicketDTOin(final long ticketIdd,
      final EStatus ticketStatuss,
      final String commentt) {
    super();
    this.ticketId = ticketIdd;
    this.ticketStatus = ticketStatuss;
    this.comment = commentt;
  }

  /**
  * Default constructor.
  */
  public UpdateTicketDTOin() {
    super();
  }

  /**
   * Override of hashCode method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(comment, ticketId, ticketStatus);
  }

  /**
  * Override of equals method.
  */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UpdateTicketDTOin other = (UpdateTicketDTOin) obj;
    return Objects.equals(comment, other.comment)
      && ticketId == other.ticketId
      && ticketStatus == other.ticketStatus;
  }
}
