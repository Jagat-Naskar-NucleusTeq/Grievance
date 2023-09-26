package com.feedback.custom_exception.Handler;

import com.feedback.custom_exception.DepartmentNotFoundException;
import com.feedback.custom_exception.TicketNotFoundException;
import com.feedback.custom_exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ControllerExceptionHandler class.
 *
 * @author jagat.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

  /**
   * Department not found exception handler.
   *
   * @param ex
   *
   * @return departmentNotFoundException
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @org.springframework.web.bind.annotation.ExceptionHandler(
      DepartmentNotFoundException.class
  )
  public String departmentNotFoundException(final DepartmentNotFoundException ex) {
    return ex.getMessage();
  }

  /**
   * UserNotFoundException handler.
   *
   * @param ex
   *
   * @return userNotFoundException
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
  public String userNotFoundException(final UserNotFoundException ex) {
    return ex.getMessage();
  }

  /**
   * TicketNotFoundException handler.
   *
   * @param ex
   *
   * @return TicketNotFoundException
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @org.springframework.web.bind.annotation.ExceptionHandler(TicketNotFoundException.class)
  public String TicketNotFoundException(final TicketNotFoundException ex) {
    return ex.getMessage();
  }
}
