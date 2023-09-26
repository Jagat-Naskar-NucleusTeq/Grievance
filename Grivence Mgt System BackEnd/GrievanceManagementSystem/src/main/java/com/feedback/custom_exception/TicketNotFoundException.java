package com.feedback.custom_exception;

/**
 * TicketNotFoundException class.
 *
 * @author jagat.
 */
public class TicketNotFoundException extends RuntimeException {

  /**
   * serialId of DepartmentNotFoundException.
   */
  private static final long serialVersionUID = 1L;

  /**
   * TicketNotFoundException, string constructor.
   *
   * @param notFound
   *
   */
  public TicketNotFoundException(final String notFound) {
    super(notFound);
  }

  /**
   * TicketNotFoundException, int constructor.
   *
   * @param userId
   *
   */
  public TicketNotFoundException(final int userId) {
    super("Ticket not found with id: " + userId);
  }
}
