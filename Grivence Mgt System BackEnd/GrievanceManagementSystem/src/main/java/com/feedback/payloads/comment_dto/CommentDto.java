package com.feedback.payloads.comment_dto;

import com.feedback.entities.Ticket;
import com.feedback.entities.User;

/**
 * DTO class for Comment entity.
 */
public class CommentDto {

  /**
   * Identifier for the comment.
   */
  private int commentId;

  /**
   * Message content of the comment.
   */
  private String commentMessage;

  /**
   * User associated with the comment (Many-to-One relationship).
   */
  private User user1;

  /**
   * Ticket associated with the comment.
   */
  private Ticket ticket;

  /**
   * Getter for the associated User.
   *
   * @return The associated User.
   *
   */
  public User getUser1() {
    return user1;
  }

  /**
   * Setter for the associated User.
   *
   * @param user11
   *
   */
  public void setUser1(final User user11) {
    this.user1 = user11;
  }

  /**
   * Getter for the associated Ticket.
   *
   * @return The associated Ticket.
   */
  public Ticket getTicket() {
    return ticket;
  }

  /**
   * Setter for the associated Ticket.
   *
   * @param tickett
   *
   */
  public void setTicket(final Ticket tickett) {
    this.ticket = tickett;
  }

  /**
   * Getter for the commentId.
   *
   * @return commentId
   */
  public int getCommentId() {
    return commentId;
  }

  /**
   * Setter for the commentId.
   *
   * @param commentIdd
   *
   */
  public void setCommentId(final int commentIdd) {
    this.commentId = commentIdd;
  }

  /**
   * Getter for the commentMessage.
   *
   * @return commentMessage.
   */
  public String getCommentMessage() {
    return commentMessage;
  }

  /**
   * Setter for the commentMessage.
   *
   * @param commentMessagee
   *
   */
  public void setCommentMessage(final String commentMessagee) {
    this.commentMessage = commentMessagee;
  }

  /**
   * Constructor with fields.
   *
   * @param commentIdd
   *
   * @param commentMessagee
   *
   * @param user11
   *
   */
  public CommentDto(final int commentIdd,
      final String commentMessagee,
      final User user11) {
    this.commentId = commentIdd;
    this.commentMessage = commentMessagee;
    this.user1 = user11;
  }

  /**
   * Default constructor.
   */
  public CommentDto() {
    // Default constructor
  }

  /**
   * Constructor with fields including Ticket.
   *
   * @param commentIdd
   *
   * @param commentMessagee
   *
   * @param user11
   *
   * @param tickett
   *
   */
  public CommentDto(final int commentIdd,
          final String commentMessagee,
          final User user11,
          final Ticket tickett) {
    this.commentId = commentIdd;
    this.commentMessage = commentMessagee;
    this.user1 = user11;
    this.ticket = tickett;
  }
}
