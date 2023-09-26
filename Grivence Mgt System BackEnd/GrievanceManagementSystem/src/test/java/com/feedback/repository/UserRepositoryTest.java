package com.feedback.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.feedback.entities.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  /**
   * failed to fetch user data.
   */
  @Test
  public void testGetUserByUsername_NonExistingUser_ReturnsNull() {
    User foundUser = userRepository.getUserByUsername("nonexistentuser");
    assertNull(foundUser);
  }
 
}
