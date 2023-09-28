package com.feedback.payloads.user_dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginDtoTest {

  private LoginDto loginDto;

  @BeforeEach
  public void setUp() {
    loginDto = new LoginDto();
  }

  @Test
  public void testDefaultConstructor() {
    assertNull(loginDto.getEmail());
    assertNull(loginDto.getPassword());
    assertNull(loginDto.getIsFirstLogin());
  }

  @Test
  public void testParameterizedConstructor() {
    LoginDto loginDto = new LoginDto("jagat@nucleusteq.com", "password123@Jme");

    assertEquals("jagat@nucleusteq.com", loginDto.getEmail());
    assertEquals("password123@Jme", loginDto.getPassword());
    assertNull(loginDto.getIsFirstLogin());
  }

  @Test
  public void testSettersAndGetters() {
    loginDto.setEmail("jagat@nucleusteq.com");
    loginDto.setPassword("password123@Jme");
    loginDto.setIsFirstLogin(true);

    assertEquals("jagat@nucleusteq.com", loginDto.getEmail());
    assertEquals("password123@Jme", loginDto.getPassword());
    assertTrue(loginDto.getIsFirstLogin());
  }

  @Test
  public void testEqualsAndHashCode() {
    LoginDto loginDto1 = new LoginDto("jme@nucleusteq.com", "password123@Jme");
    LoginDto loginDto2 = new LoginDto("jme@nucleusteq.com", "password123@Jme");
    LoginDto loginDto3 = new LoginDto("jmejagat@nucleusteq.com", "password456");

    assertEquals(loginDto1, loginDto2);
    assertNotEquals(loginDto1, loginDto3);

    assertEquals(loginDto1.hashCode(), loginDto2.hashCode());
    assertNotEquals(loginDto1.hashCode(), loginDto3.hashCode());
  }

  @Test
  public void testToString() {
    LoginDto loginDto = new LoginDto("jagat@nucleusteq.com", "password123@Jme");
    String expectedToString = "LoginDto [email=jagat@nucleusteq.com, password=password123@Jme]";
    assertEquals(expectedToString, loginDto.toString());
  }

  @Test
  void testEquals_SameObject() {
      LoginDto loginDto = new LoginDto("jmejagat@nucleusteq.com", "password123");
      assertTrue(loginDto.equals(loginDto));
  }

  @Test
  void testEquals_NullObject() {
      LoginDto loginDto = new LoginDto("jmejagat@nucleusteq.com", "password@123");
      assertFalse(loginDto.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
      LoginDto loginDto = new LoginDto("jmejagat@nucleusteq.com", "jagat@123");
      assertFalse(loginDto.equals("false_Object"));
  }

  @Test
  void testEquals_EqualObjects() {
      LoginDto loginDto1 = new LoginDto("jmejagat@nucleusteq.com", "passwordR");
      LoginDto loginDto2 = new LoginDto("jmejagat@nucleusteq.com", "passwordR");
      assertTrue(loginDto1.equals(loginDto2));
  }

  @Test
  void testEquals_UnequalObjects() {
      LoginDto loginDto1 = new LoginDto("jmejagat@nucleusteq.com", "password");
      LoginDto loginDto2 = new LoginDto("jmejagat@nucleusteq.com", "false_password");
      assertFalse(loginDto1.equals(loginDto2));
  }

}

