package com.feedback.custom_exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepartmentNotFoundExceptionTest {

  @Test
  void testConstructor() {
    String departmentName = "Sales";
    DepartmentNotFoundException exception = new DepartmentNotFoundException(departmentName);

    assertEquals("Department not found with name: Sales", exception.getMessage());
  }

}
