package com.feedback.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Represents a ticket entity in the system.
 */
@Entity
@Table(name = "Ticket")
public class Ticket {

  /**
   * The Id of the ticket.
  */
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ticketId;

  /**
   * The title of the ticket.
  */
  @Column
  private String ticketTitle;

  /**
   * The type of the ticket.
  */
  @Column
  private String ticketType;

  /**
   * The status of the ticket.
  */
  @Column
  @Enumerated(EnumType.STRING)
  private EStatus ticketStatus;

  /**
   * The user who assigned the ticket.
  */
  @Column
  private String ticketAssignedBy;

  /**
   * The creation time of the ticket.
  */
  @Column
  @CreationTimestamp
  private LocalDateTime ticketCreationTime;

  /**
   * The last updated time of the ticket.
  */
  @Column
  @UpdateTimestamp
  private LocalDateTime lastUpdatedTime;

  /**
   * ticketDescription.
   */
  @Column
  private String ticketDescription;

  /**
   * getting ticket description.
   *
   * @return ticketDescription
   */
  public String getTicketDescription() {
    return ticketDescription;
  }

  /**
   * setting ticket description.
   *
   * @param ticketDescriptionn
   *
   */
  public void setTicketDescription(final String ticketDescriptionn) {
    this.ticketDescription = ticketDescriptionn;
  }

  /**
   * User object.
   * mapped by ManyToOne relationship with Ticket.
   */
  @ManyToOne
  @JoinColumn(name = "userId")
  private User createdBy;

  /**
   * Department object.
   * Many to one mapped.
   */
  @ManyToOne
  @JoinColumn(name = "deptId")
  private Department assignedDepartment;

  /**
   * List of comment object to map
   * OneToMany mapped.
   */
  @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  /**
   * Default constructor.
  */
  public Ticket() { }

  /**
   * get user object.
   *
   * @return createdBy
   */
  public User getUser() {
    return createdBy;
  }

  /**
   * set user object.
   *
   * @param userr
   *
   */
  public void setUser(final User userr) {
    this.createdBy = userr;
  }

  /**
   * get department object.
   *
   * @return assignedDepartment
   */
  public Department getDepartment() {
    return assignedDepartment;
  }

  /**\
   * set department object.
   *
   * @param departmentt
   *
   */
  public void setDepartment(final Department departmentt) {
    this.assignedDepartment = departmentt;
  }

  /**
   * Get the id of the ticket.
   *
   * @return The ticket title.
   */
  public Long getTicketId() {
    return ticketId;
  }

  /**
   * Set the id of the ticket.
   *
   * @param l
   *
   */
  public void setTicketId(final long l) {
    this.ticketId = l;
  }

  /**
   * setTicketId.
   *
   * @param ticketIdd
   *
   */
  public void setTicketId(final Long ticketIdd) {
    this.ticketId = ticketIdd;
  }

  /**
   * Get the title of the ticket.
   *
   * @return The ticket title.
   */
  public String getTicketTitle() {
    return ticketTitle;
  }

  /**
   * Set the title of the ticket.
   *
   * @param ticketTitlee The new ticket title.
   */
  public void setTicketTitle(final String ticketTitlee) {
    this.ticketTitle = ticketTitlee;
  }

  /**
   * Get the type of the ticket.
   *
   * @return The ticket type.
   */
  public String getTicketType() {
    return ticketType;
  }

  /**
   * Set the type of the ticket.
   *
   * @param ticketTypee The new ticket type.
   */
  public void setTicketType(final String ticketTypee) {
    this.ticketType = ticketTypee;
  }

  /**
   * Get the status of the ticket.
   *
   * @return The ticket status.
   */
  public EStatus getTicketStatus() {
    return ticketStatus;
  }

  /**
   * Set the status of the ticket.
   *
   * @param ticketStatuss The new ticket status.
   */
  public void setTicketStatus(final EStatus ticketStatuss) {
    this.ticketStatus = ticketStatuss;
  }

  /**
   * Get the user who assigned the ticket.
   *
   * @return The user who assigned the ticket.
   */
  public String getTicketAssignedBy() {
    return ticketAssignedBy;
  }

  /**
   * Set the user who assigned the ticket.
   *
   * @param ticketAssignedByy The user who assigned the ticket.
   */
  public void setTicketAssignedBy(final String ticketAssignedByy) {
    this.ticketAssignedBy = ticketAssignedByy;
  }

  /**
   * Get the creation time of the ticket.
   *
   * @return The ticket creation time.
   */
  public LocalDateTime getTicketCreationTime() {
    return ticketCreationTime;
  }

  /**
   * Set the creation time of the ticket.
   *
   * @param ticketCreationTimee The new ticket creation time.
   */
  public void setTicketCreationTime(final LocalDateTime ticketCreationTimee) {
    this.ticketCreationTime = ticketCreationTimee;
  }

  /**
  * Get the last updated time of the ticket.
  *
  * @return The ticket's last updated time.
  */
  public LocalDateTime getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  /**
   * setUpdated time.
   *
   * @param lastUpdatedTimee
   *
   */
  public void setLastUpdatedTime(final LocalDateTime lastUpdatedTimee) {
    this.lastUpdatedTime = lastUpdatedTimee;
  }

  /**
   * field parameter constructor.
   *
   * @param ticketIdd
   *
   * @param ticketTitlee
   *
   * @param ticketTypee
   *
   * @param ticketStatuss
   *
   * @param ticketAssignedByy
   *
   * @param ticketCreationTimee
   *
   * @param lastUpdatedTimee
   *
   * @param ticketDescriptionn
   *
   * @param userr
   *
   * @param departmentt
   *
   * @param commentListt
   *
   */
  public Ticket(final Long ticketIdd,
      final String ticketTitlee,
      final String ticketTypee,
      final EStatus ticketStatuss,
      final String ticketAssignedByy,
      final LocalDateTime ticketCreationTimee,
      final LocalDateTime lastUpdatedTimee,
      final String ticketDescriptionn,
      final User userr,
      final Department departmentt,
      final List<Comment> commentListt) {
    super();
    this.ticketId = ticketIdd;
    this.ticketTitle = ticketTitlee;
    this.ticketType = ticketTypee;
    this.ticketStatus = ticketStatuss;
    this.ticketAssignedBy = ticketAssignedByy;
    this.ticketCreationTime = ticketCreationTimee;
    this.lastUpdatedTime = lastUpdatedTimee;
    this.ticketDescription = ticketDescriptionn;
    this.createdBy = userr;
    this.assignedDepartment = departmentt;
    this.comments = commentListt;
  }

  /**
   * getCreatedBy.
   *
   * @return createdBy
   */
  public User getCreatedBy() {
    return createdBy;
  }

  /**
   * setCreatedBy.
   *
   * @param createdByy
   *
   */
  public void setCreatedBy(final User createdByy) {
    this.createdBy = createdByy;
  }

  /**
   * getAssignedDepartment.
   *
   * @return assignedDepartment
   */
  public Department getAssignedDepartment() {
    return assignedDepartment;
  }

  /**
   * setAssignedDepartment.
   *
   * @param assignedDepartmentt
   *
   */
  public void setAssignedDepartment(final Department assignedDepartmentt) {
    this.assignedDepartment = assignedDepartmentt;
  }

  /**
   * getComments.
   *
   * @return comments
   */
  public List<Comment> getComments() {
    return comments;
  }

  /**
   * setComments.
   *
   * @param commentss
   *
   */
  public void setComments(final List<Comment> commentss) {
    this.comments = commentss;
  }

  /**
   * addComment method.
   *
   * @param stt
   *
   */
  public void addComment(final String stt) {
    if (stt == null) {
      comments = new ArrayList<>();
    } else {
      comments.add(new Comment(stt));
    }
  }
}
