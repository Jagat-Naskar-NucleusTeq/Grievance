package com.feedback.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import com.feedback.entities.User;
import com.feedback.repository.UserRepository;
import com.feedback.service.AuthorizationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * AuthenticationService class.
 */
@Service
public class AuthenticationServiceImpl implements AuthorizationService {

  /**
   * UserRepository object.
   */
  @Autowired
  private UserRepository userRepository;

  /**
   * Logger initialization.
   */
  private static final Logger LOGGER = LogManager
        .getLogger(AuthenticationServiceImpl.class);

  /**
   * Authenticates an admin user.
   *
   * @param username The admin user's userName.
   *
   * @param password The admin user's password.
   *
   * @return True if authentication is successful, otherwise false.
   */
  @Override
  public boolean authenticateAdmin(
      final String username, final String password) {
    System.out.println("uuuuu = " + username);
    System.out.println("ppppppp = " + password);
    if (userRepository.existsByUserName(username)) {
      User user = userRepository.getUserByUsername(username);
      if (password.equals(user.getPassword())
              && user.getUserType().toString().equals("admin")) {
          LOGGER.info("Authorised = " + true);
          return true;
      }
    }
    LOGGER.info("Authorised = " + false);
    return false;
  }
}
