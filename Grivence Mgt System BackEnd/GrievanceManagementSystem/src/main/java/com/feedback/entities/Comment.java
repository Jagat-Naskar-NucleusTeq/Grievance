package com.feedback.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Comment class.
 */

@Entity
@Table(name = "Comment")
public class Comment {
  /**
   * commentId to store.
   */
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int commentId;

  /**
   * commentMessage to store comments by the user.
   */
  @Column
  private String commentMessage;

  /**
   * Comment constructor.
   *
   * @param st
   *
   */
  public Comment(final String st) {
    this.commentMessage = st;
  }

  /**
   * Comment to User mapping.
   * Many to One relationship
   */
  @JoinColumn(name = "userId")
  @ManyToOne
  private User user;

  /**
   * getUser1 object.
   *
   * @return user
   *
   */
  public User getUser1() {
    return user;
  }

  /**
   * setUser1.
   *
   * @param userr
   *
   */
  public void setUser1(final User userr) {
    this.user = userr;
  }

  /**
   * Ticket object.
   */
  @JoinColumn(name = "ticketId")
  @ManyToOne
  private Ticket ticket;

  /**
   * getTicket function.
   *
   * @return ticket
   *
   */
  public Ticket getTicket() {
    return ticket;
  }

  /**
   * setTicket.
   *
   *@param tickett
   *
   */
  public void setTicket(final Ticket tickett) {
    this.ticket = tickett;
  }

  /**
   * get commit id.
   *
   * @return commentId
   *
   */
  public int getCommentId() {
    return commentId;
  }

  /**
   * setCommentId.
   *
   * @param commentIdd
   *
   */
  public void setCommentId(final int commentIdd) {
    this.commentId = commentIdd;
  }

  /**
   * get message commentl.
   *
   * @return commentMessage.
   */
  public String getCommentMessage() {
    return commentMessage;
  }

  /**
   * set comment message.
   *
   * @param commentMessagee
   *
   */
  public void setCommentMessage(final String commentMessagee) {
    this.commentMessage = commentMessagee;
  }

  /**
   * field constructor.
   *
   * @param commentIdd
   *
   * @param commentMessagee
   *
   * @param userr
   *
 */
  public Comment(final int commentIdd,
      final String commentMessagee,
      final User userr) {
    super();
    this.commentId = commentIdd;
    this.commentMessage = commentMessagee;
    this.user = userr;
  }

  /**
   * non parameterized constructor.
   */
  public Comment() {
    super();
  }
}
