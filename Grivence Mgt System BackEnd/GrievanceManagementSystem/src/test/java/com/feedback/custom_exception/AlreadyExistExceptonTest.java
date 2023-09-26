package com.feedback.custom_exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlreadyExistExceptonTest {

  @Test
  void testDefaultConstructor() {
    AlreadyExistExcepton exception = new AlreadyExistExcepton();

    assertEquals("Already exist", exception.getMessage());
  }

  @Test
  void testConstructorWithId() {
    long id = 123L;
    AlreadyExistExcepton exception = new AlreadyExistExcepton(id);

    assertEquals("123 Already exist", exception.getMessage());
  }

  @Test
  void testConstructorWithName() {
    String name = "John Doe";
    AlreadyExistExcepton exception = new AlreadyExistExcepton(name);

    assertEquals("John Doe Already exist", exception.getMessage());
  }
}
