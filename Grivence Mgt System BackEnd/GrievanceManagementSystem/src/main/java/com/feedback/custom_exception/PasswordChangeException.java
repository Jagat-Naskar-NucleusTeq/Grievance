package com.feedback.custom_exception;

/**
 * PasswordChangeException class.
 *
 * @author jagat.
 */
public class PasswordChangeException extends RuntimeException {

  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * PasswordChangeException constructor with string argument.
   *
   * @param message
   *
   */
  public PasswordChangeException(final String message) {
    super(message);
  }
}
