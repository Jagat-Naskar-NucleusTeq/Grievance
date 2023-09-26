package com.feedback.custom_exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordChangeExceptionTest {

 @Test
    public void testMessageConstructor() {
        PasswordChangeException exception = new PasswordChangeException("Password change failed!");

        assertEquals("Password change failed!", exception.getMessage());
    }

}
