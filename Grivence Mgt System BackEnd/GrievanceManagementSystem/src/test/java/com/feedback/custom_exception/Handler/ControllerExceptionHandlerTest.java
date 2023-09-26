package com.feedback.custom_exception.Handler;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.feedback.custom_exception.DepartmentNotFoundException;
import com.feedback.custom_exception.TicketNotFoundException;
import com.feedback.custom_exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

	@InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    @Test
    void departmentNotFoundExceptionHandler() {
        DepartmentNotFoundException ex = new DepartmentNotFoundException();
        String result = controllerExceptionHandler.departmentNotFoundException(ex);
        assertEquals("Department not found", result);
    }

    @Test
    void userNotFoundExceptionHandler() {
    	UserNotFoundException exception = new UserNotFoundException("User not found");
        ControllerExceptionHandler controllerExceptionHandler = new ControllerExceptionHandler();

        // Act
        String result = controllerExceptionHandler.userNotFoundException(exception);

        // Assert
        assertEquals("User not found with name: User not found", result);
    }

    @Test
    void ticketNotFoundExceptionHandler() {
        TicketNotFoundException ex = new TicketNotFoundException("Ticket not found");
        String result = controllerExceptionHandler.TicketNotFoundException(ex);
        assertEquals("Ticket not found", result);
    }
}
