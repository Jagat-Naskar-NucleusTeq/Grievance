package com.feedback.custom_exception;

/**
 * ResourceNotFoundException class.
 *
 * @author jagat.
 */
public class ResourceNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * resourceName string variable.
   */
  String resourceName;

  /**
   * FieldName string variable.
   */
  String FieldName;

  /**
   * fieldValue long variable.
   */
  Long fieldValue;

  /**
   * Resource not found field constructor.
   *
   * @param resourceNamee
   *
   * @param fieldNamee
   *
   * @param fieldValuee
   *
   */
  public ResourceNotFoundException(
      final String resourceNamee,
      final String fieldNamee,
      final long fieldValuee
  ) {
    super(
        String.format("%s not found with %s : %s",
            resourceNamee, fieldNamee, fieldValuee)
    );
    this.resourceName = resourceNamee;
    FieldName = fieldNamee;
    this.fieldValue = fieldValuee;
  }
}
