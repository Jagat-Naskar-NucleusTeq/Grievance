package com.feedback.custom_exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {

  @Test
  void testConstructor() {
      String resourceName = "Department";
      String fieldName = "deptId";
      long fieldValue = 123;

      ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

      String expectedMessage = String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue);
      assertEquals(expectedMessage, exception.getMessage());
      assertEquals(resourceName, exception.resourceName);
      assertEquals(fieldName, exception.FieldName);
      assertEquals(fieldValue, exception.fieldValue);
  }

}
