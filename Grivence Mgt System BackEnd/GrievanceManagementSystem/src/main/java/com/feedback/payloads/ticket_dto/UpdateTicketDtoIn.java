package com.feedback.payloads.ticket_dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.feedback.entities.Estatus;

/**
 * DTO class for updating a ticket.
 */
public class UpdateTicketDtoIn {
  /**
  * The ID of the ticket.
  */
  @NotNull(message = "Ticket id is required")
  private long ticketId;

  /**
  * The status of the ticket.
  */
  private Estatus ticketStatus;

  /**
   * UserName variable.
   */
  private String userName;

  /**
  * The comment for the ticket.
  */
  @NotEmpty(message = "comment is required")
  private String commentMessage;

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
  public Estatus getTicketStatus() {
    return ticketStatus;
  }

  /**
  * Setter for ticket status.
  *
  * @param ticketStatuss
  *
  */
  public void setTicketStatus(final Estatus ticketStatuss) {
    this.ticketStatus = ticketStatuss;
  }

  /**
  * Getter for commentMessage.
  *
  * @return The commentMessage.
  */
  public String getCommentMessage() {
    return commentMessage;
  }

  /**
  * Setter for commentMessage.
  *
  * @param commentMessagee
  *
  */
  public void setcommentMessageList(final String commentMessagee) {
    this.commentMessage = commentMessagee;
  }

  /**
   * get userName.
  * @return the userName.
  *
  */

  public String getUserName() {
    return userName;
  }

  /**
  * set UserName.
  *
  * @param userNamee the userName to set
  *
  */
  public void setUserName(String userNamee) {
    this.userName = userNamee;
  }

  /**
  * setcommentMessage.
  *
  * @param commentMessagee the comment to set
-  *
  */
  public void setComment(String commentMessagee) {
    this.commentMessage = commentMessagee;
  }

/**
  * Override of toString method.
  */
  @Override
  public String toString() {
    return "UpdateTicketDtoIn [ticketId=" + ticketId
          + ", ticketStatus=" + ticketStatus
          + ", userName=" + userName
          + ", commentMessage=" + commentMessage
          + "]";
  }

  /**
   * hashCode method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(commentMessage, ticketId, ticketStatus, userName);
  }

  /**
   * equals method.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
        return true;
        }
    if (obj == null) {
        return false;
        }
    if (getClass() != obj.getClass()) {
        return false;
        }
    UpdateTicketDtoIn other = (UpdateTicketDtoIn) obj;
    return Objects.equals(commentMessage, other.commentMessage) && ticketId == other.ticketId
            && ticketStatus == other.ticketStatus
            && Objects.equals(userName, other.userName);
  }

  /**
   * UpdateTicketDtoIn field constructor.
   *
   * @param ticketIdd
   *
   * @param ticketStatuss
   *
   * @param userNamee
   *
   * @param commentMessagee
   *
   */
  public UpdateTicketDtoIn(
        @NotNull(message = "Ticket id is required") long ticketIdd,
        Estatus ticketStatuss, String userNamee,
        @NotEmpty(message = "comment is required") String commentMessagee) {
    super();
    this.ticketId = ticketIdd;
    this.ticketStatus = ticketStatuss;
    this.userName = userNamee;
    this.commentMessage = commentMessagee;
  }

  /**
   * UpdateTicketDtoIn constructor.
   */
  public UpdateTicketDtoIn() {
    super();
    //TODO Auto-generated constructor stub
  }
}
