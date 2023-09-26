package com.feedback.custom_exception;

/**
 * UserNotFoundException class.
 *
 * @author jagat.
 */
public class UserNotFoundException extends RuntimeException {
  /**
   * serialId of DepartmentNotFoundException.
   */
  private static final long serialVersionUID = 1L;

  /**
   * UserNotFoundException, string constructor.
   *
   * @param userName
   *
   */
  public UserNotFoundException(final String userName) {
    super("User not found with name: " + userName);
  }

  /**
   * UserNotFoundException, no args constructor.
   */
  public UserNotFoundException() {
    super("Empty user list.");
  }

  /**
   * UserNotFoundException, int constructor.
   *
   * @param userId
   *
   */
  public UserNotFoundException(final int userId) {
    super("User not found with id: " + userId);
  }
}
