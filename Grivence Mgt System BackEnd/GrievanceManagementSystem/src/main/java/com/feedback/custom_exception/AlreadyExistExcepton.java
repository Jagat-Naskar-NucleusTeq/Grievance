package com.feedback.custom_exception;

/**
 * AlreadyExistExcepton class.
 *
 * @author jagat.
 */
public class AlreadyExistExcepton extends RuntimeException {
  /**
   *serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * no argument constructor.
   */
  public AlreadyExistExcepton() {
    super("Already exist");
  }

  /**
   * long argument constructor.
   *
   * @param id
   *
   */
  public AlreadyExistExcepton(final Long id) {
    super(id + " Already exist");
  }

  /**
   * String argument constructor.
   *
   * @param name
   *
   */
  public AlreadyExistExcepton(final String name) {
    super(name + " Already exist");
  }
}
