package com.feedback.payloads.ticket_dto;

import com.feedback.entities.EStatus;
import java.util.Objects;

/**
 * DTO class for representing a new ticket.
 */
public class NewTicketDTO {

  /**
   * The ID of the ticket.
   */
  private Long ticketId;

  /**
   * The title of the ticket.
   */
  private String ticketTitle;

  /**
   * The type of the ticket.
   */
  private String ticketType;

  /**
   * The status of the ticket.
   */
  private EStatus ticketStatus;

  /**
   * The description of the ticket.
   */
  private String ticketDescription;

  /**
   * The email of the sender.
   */
  private String senderEmail;

  /**
   * The department name associated with the ticket.
   */
  private String deptName;

  /**
   * Getter for ticket ID.
   *
   * @return The ticket ID.
   */
  public Long getTicketId() {
    return ticketId;
  }

  /**
   * Setter for ticket ID.
   *
   * @param ticketIdd The ticket ID to be set.
   *
   */
  public void setTicketId(final Long ticketIdd) {
    this.ticketId = ticketIdd;
  }

  /**
   * Getter for ticket title.
   *
   * @return The ticket title.
   */
  public String getTicketTitle() {
    return ticketTitle;
  }

  /**
   * Setter for ticket title.
   *
   * @param ticketTitlee The ticket title to be set.
   *
   */
  public void setTicketTitle(final String ticketTitlee) {
    this.ticketTitle = ticketTitlee;
  }

  /**
   * Getter for ticket type.
   *
   * @return The ticket type.
   */
  public String getTicketType() {
    return ticketType;
  }

  /**
   * Setter for ticket type.
   *
   * @param ticketTypee The ticket type to be set.
   *
   */
  public void setTicketType(final String ticketTypee) {
    this.ticketType = ticketTypee;
  }

  /**
   * Getter for ticket status.
   *
   * @return The ticket status.
   */
  public EStatus getTicketStatus() {
    return ticketStatus;
  }

  /**
   * Setter for ticket status.
   *
   * @param ticketStatuss The ticket status to be set.
   *
   */
  public void setTicketStatus(final EStatus ticketStatuss) {
    this.ticketStatus = ticketStatuss;
  }

  /**
   * Getter for ticket description.
   *
   * @return ticketDescription.
   */
  public String getTicketDescription() {
    return ticketDescription;
  }

  /**
   * Setter for ticket description.
   *
   * @param ticketDescriptionn The ticket description to be set.
   *
   */
  public void setTicketDescription(final String ticketDescriptionn) {
    this.ticketDescription = ticketDescriptionn;
  }

  /**
   * Getter for sender's email.
   *
   * @return The sender's email.
   */
  public String getSenderEmail() {
    return senderEmail;
  }

  /**
   * Setter for sender's email.
   *
   * @param senderEmaill The sender's email to be set.
   *
   */
  public void setSenderEmail(final String senderEmaill) {
    this.senderEmail = senderEmaill;
  }

  /**
   * Getter for department name.
   *
   * @return The department name.
   */
  public String getDeptName() {
    return deptName;
  }

  /**
   * Setter for department name.
   *
   * @param deptNamee The department name to be set.
   *
   */
  public void setDeptName(final String deptNamee) {
    this.deptName = deptNamee;
  }

  /**
   * Override of toString method.
   */
  @Override
  public String toString() {
    return "NewTicketDTO [ticketId=" + ticketId
      + ", ticketTitle=" + ticketTitle
      + ", ticketType=" + ticketType
      + ", ticketStatus=" + ticketStatus
      + ", ticketDescription=" + ticketDescription
      + ", senderEmail=" + senderEmail
      + ", deptName=" + deptName + "]";
  }

  /**
   * Constructor with parameters.
   *
   * @param ticketIdd The ID of the ticket.
   *
   * @param ticketTitlee The title of the ticket.
   *
   * @param ticketTypee The type of the ticket.
   *
   * @param ticketStatuss The status of the ticket.
   *
   * @param ticketDescriptionn The description of the ticket.
   *
   * @param senderEmaill The sender's email.
   *
   * @param deptNamee The department name.
   *
   */
  public NewTicketDTO(final Long ticketIdd,
          final String ticketTitlee,
          final String ticketTypee,
          final EStatus ticketStatuss,
          final String ticketDescriptionn,
          final String senderEmaill,
          final String deptNamee) {
    this.ticketId = ticketIdd;
    this.ticketTitle = ticketTitlee;
    this.ticketType = ticketTypee;
    this.ticketStatus = ticketStatuss;
    this.ticketDescription = ticketDescriptionn;
    this.senderEmail = senderEmaill;
    this.deptName = deptNamee;
  }

  /**
   * Default constructor.
   */
  public NewTicketDTO() {
    // Default constructor
  }

  /**
   * Override of hashCode method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(deptName,
            senderEmail,
            ticketDescription,
            ticketId,
            ticketStatus,
            ticketTitle,
            ticketType);
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
    NewTicketDTO other = (NewTicketDTO) obj;
    return Objects.equals(deptName, other.deptName)
      && Objects.equals(senderEmail, other.senderEmail)
      && Objects.equals(ticketDescription, other.ticketDescription)
      && Objects.equals(ticketId, other.ticketId)
      && ticketStatus == other.ticketStatus
      && Objects.equals(ticketTitle, other.ticketTitle)
      && Objects.equals(ticketType, other.ticketType);
  }
}
