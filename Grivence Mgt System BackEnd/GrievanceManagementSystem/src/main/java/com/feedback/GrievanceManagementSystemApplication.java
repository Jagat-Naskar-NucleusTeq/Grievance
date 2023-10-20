package com.feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main file.
 */
@SpringBootApplication
public class GrievanceManagementSystemApplication {

  /**
   *constructor.
   */
  protected GrievanceManagementSystemApplication() { }

  /**
   *main method.
   * @param args takes a string parameter as argument
   */
  public static void main(final String[] args) {
    SpringApplication.run(GrievanceManagementSystemApplication.class, args);
  }
}
