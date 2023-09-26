package com.feedback.service;

/**
 * Service interface for authorization.
 */
public interface authorization_service {

  /**
  * Authenticate an admin user.
  *
  * @param username The admin user's username.
  *
  * @param password The admin user's password.
  *
  * @return True if authentication is successful, otherwise false.
  */
  public boolean authenticateAdmin(
      final String username, final String password);
}
