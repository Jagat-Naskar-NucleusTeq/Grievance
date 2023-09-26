package com.feedback.custom_exception;

/**
 * * DepartmentNotFoundException class.
 *
 * @author jagat.
 */
public class DepartmentNotFoundException extends RuntimeException {
  /**
   * serialId of DepartmentNotFoundException.
   */
  private static final long serialVersionUID = 1L;

  /**
   * DepartmentNotFoundException constructor with String constructor.
   *
   * @param departmentName
   *
   */
  public DepartmentNotFoundException(final String departmentName) {
    super("Department not found with name: " + departmentName);
  }

  /**
   * no args constructor.
   */
  public DepartmentNotFoundException() {
    super("Department not found");
  }
}
