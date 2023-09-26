package com.feedback.custom_exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NullPointerFromFrontendExceptionTest {

  @Test
  void testConstructorWithoutParameters() {
      NullPointerFromFrontendException exception = new NullPointerFromFrontendException();

      assertEquals("Already exist", exception.getMessage());
  }

  @Test
  void testConstructorWithId() {
      long id = 123;
      NullPointerFromFrontendException exception = new NullPointerFromFrontendException(id);

      assertEquals("Object id =123 from frontend returns null.", exception.getMessage());
  }

  @Test
  void testConstructorWithName() {
      String name = "TestObject";
      NullPointerFromFrontendException exception = new NullPointerFromFrontendException(name);

      assertEquals("TestObject object from frontend returns null", exception.getMessage());
  }

  @Test
  void testConstructorWithObject() {
      Object object = new Object();
      NullPointerFromFrontendException exception = new NullPointerFromFrontendException(object);

      assertEquals(object.toString() + " object from frontend returns null", exception.getMessage());
  }
}
