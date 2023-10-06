package com.feedback.payloads.ticket_dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.feedback.entities.Estatus;
import com.feedback.payloads.comment_dto.GetCommentDtoOut;

/**
 * DTO class for retrieving ticket information.
 */
public class GetTicketDtoOut {

  /**
   * ticketId variable.
   */
  private Long ticketId;

  /**
   * title variable.
   */
  private String title;

  /**
   * creationTime variable.
   */
  private LocalDateTime creationTime;

  /**
   * updationTime variable.
   */
  private LocalDateTime updationTime;

  /**
   * ticketStatus variable.
   */
  private Estatus ticketStatus;

  /**
   * ticketType variable.
   */
  private String ticketType;

  /**
   * createdBy variable.
   */
  private String createdBy;

  /**
   * departmentName variable.
   */
  private String departmentName;

  /**
   * ticketDescription variable.
   */
  private String ticketDescription;

  /**
   * List of GetCommentDtoOut.
   */
  private List<GetCommentDtoOut> comments;

  /**
  * Getter for ticket ID.
  *
  * @return ticketId
  */
  public Long getTicketId() {
    return ticketId;
  }

  /**
  * Setting for ticket Id.
  *
  * @param ticketIdd
  *
  */
  public void setTicketId(final Long ticketIdd) {
    this.ticketId = ticketIdd;
  }

  /**
  * Getter for ticket title.
  *
  * @return title of the ticket.
  */
  public String getTitle() {
    return title;
  }

  /**
   * setTitle.
   *
   * @param titlee
   *
   */
  public void setTitle(final String titlee) {
    this.title = titlee;
  }

  /**
  * Getter for creation time.
  *
  * @return creationTime of the ticket.
  */
  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  /**
  * Setter for creation time.
  *
  * @param creationTimee
  *
  */
  public void setCreationTime(final LocalDateTime creationTimee) {
    this.creationTime = creationTimee;
  }

  /**
  * Getter for updation time.
  *
  * @return updationTime of the ticket.
  */
  public LocalDateTime getUpdationTime() {
    return updationTime;
  }

  /**
  * Setter for updation time.
  *
  * @param updationTimee
  *
  */
  public void setUpdationTime(final LocalDateTime updationTimee) {
    this.updationTime = updationTimee;
  }

  /**
   * getTicketDescription.
   *
   * @return ticketDescription.
   */
  public String getTicketDescription() {
    return ticketDescription;
  }

  /**
   * setTicketDescription.
   *
   * @param ticketDescriptionn
   *
   */
  public void setTicketDescription(final String ticketDescriptionn) {
    this.ticketDescription = ticketDescriptionn;
  }

  /**
  * Getter for ticket status.
  *
     * @return ticketStatus of the ticket.
  */
  public Estatus getTicketStatus() {
    return ticketStatus;
  }

  /**
  * Setter for ticket status.
  *
  * @param eStatuss
  *
  */
  public void setTicketStatus(final Estatus eStatuss) {
    this.ticketStatus = eStatuss;
  }

  /**
  * Getter for ticket type.
  *
  * @return ticketType of the ticket.
  */
  public String getTicketType() {
    return ticketType;
  }

  /**
  * Setter for ticket type.
  *
  * @param ticketTypee
  *
  */
  public void setTicketType(final String ticketTypee) {
    this.ticketType = ticketTypee;
  }

  /**
  * Getter for creator information.
  *
  * @return createdBy of the ticket.
  */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
  * Setter for creator information.
  *
  * @param createdByy
  *
  */
  public void setCreatedBy(final String createdByy) {
    this.createdBy = createdByy;
  }

  /**
  * Getter for department name.
  *
  * @return departmentName of the department associated with the ticket.
  */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
  * Setter for department name.
  *
  * @param departmentNamee of the department associated with the ticket.
  *
  */
  public void setDepartmentName(final String departmentNamee) {
    this.departmentName = departmentNamee;
  }

  /**
  * Getter for comments associated with the ticket.
  *
  * @return The list of comments.
  */
  public List<GetCommentDtoOut> getComments() {
    return comments;
  }

  /**
  * Setter for comments associated with the ticket.
  *
  * @param commentDTOss
  *
  */
  public void setComments(
      final List<GetCommentDtoOut> commentDTOss) {
    this.comments = commentDTOss;
  }

  /**
  * Constructor with parameters.
  *
  * @param ticketIdd The ID of the ticket.
  *
  * @param titlee The title of the ticket.
  *
  * @param creationTimee The creation time of the ticket.
  *
  * @param updationTimee The updation time of the ticket.
  *
  * @param ticketStatuss The status of the ticket.
  *
  * @param ticketTypee The type of the ticket.
  *
  * @param createdByy The creator of the ticket.
  *
  * @param ticketDescriptionn
  *
  * @param departmentNamee
  *
  * @param commentss The list of comments associated with the ticket.
  *
  */
  public GetTicketDtoOut(final Long ticketIdd,
      final String titlee,
      final LocalDateTime creationTimee,
      final LocalDateTime updationTimee,
      final Estatus ticketStatuss,
      final String ticketTypee,
      final String createdByy,
      final String ticketDescriptionn,
      final String departmentNamee,
      final List<GetCommentDtoOut> commentss) {
    super();
    this.ticketId = ticketIdd;
    this.title = titlee;
    this.creationTime = creationTimee;
    this.updationTime = updationTimee;
    this.ticketStatus = ticketStatuss;
    this.ticketDescription = ticketDescriptionn;
    this.ticketType = ticketTypee;
    this.createdBy = createdByy;
    this.departmentName = departmentNamee;
    this.comments = commentss;
  }

  /**
   * Default constructor.
   */
  public GetTicketDtoOut() {
    super();
  }

  /**
   * toString.
   */
  @Override
  public String toString() {
      return "GetTicketDtoOut [ticketId=" + ticketId
          + ", title=" + title
          + ", creationTime=" + creationTime
          + ", updationTime=" + updationTime
          + ", ticketStatus=" + ticketStatus
          + ", ticketType=" + ticketType
          + ", createdBy=" + createdBy
          + ", departmentName=" + departmentName
          + ", ticketDescription=" + ticketDescription
          + ", comments=" + comments + "]";
  }

    /**
    * Override of hashCode method.
    */
    @Override
    public int hashCode() {
      return Objects.hash(comments, createdBy, creationTime,
        departmentName, ticketDescription, ticketId, ticketStatus,
        ticketType, title, updationTime);
    }

  /**
  * equals method.
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
    GetTicketDtoOut other = (GetTicketDtoOut) obj;
    return Objects.equals(comments, other.comments)
        && Objects.equals(createdBy, other.createdBy)
        && Objects.equals(creationTime, other.creationTime)
        && Objects.equals(departmentName, other.departmentName)
        && Objects.equals(ticketDescription, other.ticketDescription)
        && Objects.equals(ticketId, other.ticketId)
        && ticketStatus == other.ticketStatus
        && Objects.equals(ticketType, other.ticketType)
        && Objects.equals(title, other.title)
        && Objects.equals(updationTime, other.updationTime);
  }
}
