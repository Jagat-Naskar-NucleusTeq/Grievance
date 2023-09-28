package com.feedback.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.feedback.entities.ERole;
import com.feedback.entities.User;
import com.feedback.repository.UserRepository;

@SpringBootTest
class AuthenticationServiceImplTest {

    @Autowired
    private final AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl();

    @Autowired
    @MockBean
    UserRepository userRepository;

    @Test
    void testAuthenticateAdmin_Success() {
        User adminUser = new User();
        adminUser.setUserName("jme@nucleusteq.com");
        adminUser.setPassword("password123");
        adminUser.setUserType(ERole.admin);

        when(userRepository.existsByUserName("jme@nucleusteq.com")).thenReturn(true);
        when(userRepository.getUserByUsername("jme@nucleusteq.com")).thenReturn(adminUser);

        boolean result = authenticationServiceImpl.authenticateAdmin("jme@nucleusteq.com", "password123");

        assertTrue(result);
    }

    @Test
    void testAuthenticateAdmin_InvalidUsername() {
        when(userRepository.existsByUserName(anyString())).thenReturn(false);

        boolean result = authenticationServiceImpl.authenticateAdmin("nonexistent", "password123");

        assertFalse(result);
    }

    @Test
    void testAuthenticateAdmin_IncorrectPassword() {
        User adminUser = new User();
        adminUser.setUserName("am1lQG51Y2xldXN0ZXEuY29t");
        adminUser.setPassword("password123");
        adminUser.setUserType(ERole.admin);

        when(userRepository.existsByUserName("jme@nucleusteq.com")).thenReturn(true);
        when(userRepository.getUserByUsername("jme@nucleusteq.com")).thenReturn(adminUser);

        boolean result = authenticationServiceImpl.authenticateAdmin("jme@nucleusteq.com", "wrongPassword");

        assertFalse(result);
    }

    @Test
    void testAuthenticateAdmin_NonAdminUser() {
        User nonAdminUser = new User();
        nonAdminUser.setUserName("user");
        nonAdminUser.setPassword("password123");
        nonAdminUser.setUserType(ERole.member);

        when(userRepository.existsByUserName("user")).thenReturn(true);
        when(userRepository.getUserByUsername("user")).thenReturn(nonAdminUser);

        boolean result = authenticationServiceImpl.authenticateAdmin("user", "password123");

        assertFalse(result);
    }
}
