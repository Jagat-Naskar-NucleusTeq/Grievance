package com.feedback.payloads.comment_dto;

import java.util.Objects;

/**
 * GetCommentDtoOut class.
 */
public class GetCommentDtoOut {

  /**
   * commentedByUser variable.
   */
  private String commentedByUser;

  /**
   * commentMessage variable.
   */
  private String commentMessage;

  /**
   * commentId variable.
   */
  private int commentId;

  /**
  * getCommentedByUser.
  *
  * @return commentedByUser
 */
  public String getCommentedByUser() {
    return commentedByUser;
  }

  /**
   * setCommentedByUser.
   *
   * @param commentedByUserr
   *
   */
  public void setCommentedByUser(final String commentedByUserr) {
    this.commentedByUser = commentedByUserr;
  }

  /**
   * getCommentMessage.
   *
   * @return commentMessage
   */
  public String getCommentMessage() {
    return commentMessage;
  }

  /**
   * setCommentMessage.
   *
   * @param commentMessagee
   *
   */
  public void setCommentMessage(final String commentMessagee) {
    this.commentMessage = commentMessagee;
  }

  /**
   * getCommentId.
   *
   * @return commentMessage
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
   * toString method.
   */
  @Override
  public String toString() {
    return "GetCommentDtoOut [commentedByUser=" + commentedByUser
      + ", commentMessage=" + commentMessage
      + ", commentId=" + commentId + "]";
  }

  /**
   * hashCode method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(commentId, commentMessage, commentedByUser);
  }

  /**
   * object equals.
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
    GetCommentDtoOut other = (GetCommentDtoOut) obj;
    return commentId == other.commentId
            && Objects.equals(commentMessage, other.commentMessage)
            && Objects.equals(commentedByUser, other.commentedByUser);
  }

  /**
   * Constructor with parameters.
   *
   * @param commentedByUserr
   *
   * @param commentMessagee
   *
   * @param commentIdd
   *
   */
  public GetCommentDtoOut(final String commentedByUserr,
          final String commentMessagee,
          final int commentIdd) {
    super();
    this.commentedByUser = commentedByUserr;
    this.commentMessage = commentMessagee;
    this.commentId = commentIdd;
  }

  /**
   * Default constructor.
   */
  public GetCommentDtoOut() {
    super();
  }
}
