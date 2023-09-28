package com.feedback.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.entities.User;
import com.feedback.repository.UserRepository;
import com.feedback.service.AuthorizationService;

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
    User user = new User();
    System.out.println("uuuuu = " + username);
    System.out.println("ppppppp = " + password);
    if (userRepository.existsByUserName(username)) {
      user = userRepository.getUserByUsername(username);
      if (password.equals(user.getPassword())) {
        if (user.getUserType().toString().equals("admin")) {
          return true;
        }
      }
    }
    return false;
  }
}
