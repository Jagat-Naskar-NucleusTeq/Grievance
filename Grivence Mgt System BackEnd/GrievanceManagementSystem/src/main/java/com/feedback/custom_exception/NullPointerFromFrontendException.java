package com.feedback.custom_exception;

/**
 * NullPointerFromFrontendException class.
 *
 * @author jagat.
 */
public class NullPointerFromFrontendException extends RuntimeException {

  /**
   *serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * NullPointerFromFrontendException no args constructor.
   */
  public NullPointerFromFrontendException() {
    super("Already exist");
  }

  /**
   * NullPointerFromFrontendException with long args.
   *
   * @param id
   *
   */
  public NullPointerFromFrontendException(final Long id) {
    super("Object id =" + id + " from frontend returns null.");
  }

  /**
   * NullPointerFromFrontendException with string args.
   *
   * @param name
   *
   */
  public NullPointerFromFrontendException(final String name) {
    super(name + " object from frontend returns null");
  }

  /**
   * NullPointerFromFrontendException with object as args.
   *
   * @param name
   *
   */
  public NullPointerFromFrontendException(final Object name) {
    super(name + " object from frontend returns null");
  }
}
