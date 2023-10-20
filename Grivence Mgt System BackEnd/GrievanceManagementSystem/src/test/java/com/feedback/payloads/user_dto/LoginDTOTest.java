package com.feedback.payloads.user_dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginDtoTest {

  private LoginDtoIn loginDtoIn;

  @BeforeEach
  public void setUp() {
    loginDtoIn = new LoginDtoIn();
  }

  @Test
  public void testDefaultConstructor() {
    assertNull(loginDtoIn.getEmail());
    assertNull(loginDtoIn.getPassword());
    assertNull(loginDtoIn.getIsFirstLogin());
  }

  @Test
  public void testParameterizedConstructor() {
    LoginDtoIn loginDtoIn = new LoginDtoIn("jagat@nucleusteq.com", "password123@Jme");

    assertEquals("jagat@nucleusteq.com", loginDtoIn.getEmail());
    assertEquals("password123@Jme", loginDtoIn.getPassword());
    assertNull(loginDtoIn.getIsFirstLogin());
  }

  @Test
  public void testSettersAndGetters() {
    loginDtoIn.setEmail("jagat@nucleusteq.com");
    loginDtoIn.setPassword("password123@Jme");
    loginDtoIn.setIsFirstLogin(true);

    assertEquals("jagat@nucleusteq.com", loginDtoIn.getEmail());
    assertEquals("password123@Jme", loginDtoIn.getPassword());
    assertTrue(loginDtoIn.getIsFirstLogin());
  }

  @Test
  public void testEqualsAndHashCode() {
    LoginDtoIn loginDto1 = new LoginDtoIn("jme@nucleusteq.com", "password123@Jme");
    LoginDtoIn loginDto2 = new LoginDtoIn("jme@nucleusteq.com", "password123@Jme");
    LoginDtoIn loginDto3 = new LoginDtoIn("jmejagat@nucleusteq.com", "password456");

    assertEquals(loginDto1, loginDto2);
    assertNotEquals(loginDto1, loginDto3);

    assertEquals(loginDto1.hashCode(), loginDto2.hashCode());
    assertNotEquals(loginDto1.hashCode(), loginDto3.hashCode());
  }

  @Test
  public void testToString() {
    LoginDtoIn loginDtoIn = new LoginDtoIn("jagat@nucleusteq.com", "password123@Jme");
    String expectedToString = "LoginDto [email=jagat@nucleusteq.com, password=password123@Jme]";
    assertEquals(expectedToString, loginDtoIn.toString());
  }

  @Test
  void testEquals_SameObject() {
      LoginDtoIn loginDtoIn = new LoginDtoIn("jmejagat@nucleusteq.com", "password123");
      assertTrue(loginDtoIn.equals(loginDtoIn));
  }

  @Test
  void testEquals_NullObject() {
      LoginDtoIn loginDtoIn = new LoginDtoIn("jmejagat@nucleusteq.com", "password@123");
      assertFalse(loginDtoIn.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
      LoginDtoIn loginDtoIn = new LoginDtoIn("jmejagat@nucleusteq.com", "jagat@123");
      assertFalse(loginDtoIn.equals("false_Object"));
  }

  @Test
  void testEquals_EqualObjects() {
      LoginDtoIn loginDto1 = new LoginDtoIn("jmejagat@nucleusteq.com", "passwordR");
      LoginDtoIn loginDto2 = new LoginDtoIn("jmejagat@nucleusteq.com", "passwordR");
      assertTrue(loginDto1.equals(loginDto2));
  }

  @Test
  void testEquals_UnequalObjects() {
      LoginDtoIn loginDto1 = new LoginDtoIn("jmejagat@nucleusteq.com", "password");
      LoginDtoIn loginDto2 = new LoginDtoIn("jmejagat@nucleusteq.com", "false_password");
      assertFalse(loginDto1.equals(loginDto2));
  }

}

