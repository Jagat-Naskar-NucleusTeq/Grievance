package com.feedback.service;

/**
 * Service interface for authorization.
 */
public interface AuthorizationService {

  /**
  * Authenticate an admin user.
  *
  * @param username The admin user's username.
  *
  * @param password The admin user's password.
  *
  * @return True if authentication is successful, otherwise false.
  */
  boolean authenticateAdmin(
      String username, String password);
}
