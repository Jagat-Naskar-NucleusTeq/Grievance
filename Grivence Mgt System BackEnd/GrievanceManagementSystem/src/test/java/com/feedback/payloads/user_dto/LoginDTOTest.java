package com.feedback.payloads.user_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginDTOTest {

  private LoginDTO loginDTO;

  @BeforeEach
  public void setUp() {
    loginDTO = new LoginDTO();
  }

  @Test
  public void testDefaultConstructor() {
    assertNull(loginDTO.getEmail());
    assertNull(loginDTO.getPassword());
    assertNull(loginDTO.getIsFirstLogin());
  }

  @Test
  public void testParameterizedConstructor() {
    LoginDTO loginDTO = new LoginDTO("jagat@nucleusteq.com", "password123@Jme");

    assertEquals("jagat@nucleusteq.com", loginDTO.getEmail());
    assertEquals("password123@Jme", loginDTO.getPassword());
    assertNull(loginDTO.getIsFirstLogin());
  }

  @Test
  public void testSettersAndGetters() {
    loginDTO.setEmail("jagat@nucleusteq.com");
    loginDTO.setPassword("password123@Jme");
    loginDTO.setIsFirstLogin(true);

    assertEquals("jagat@nucleusteq.com", loginDTO.getEmail());
    assertEquals("password123@Jme", loginDTO.getPassword());
    assertTrue(loginDTO.getIsFirstLogin());
  }

  @Test
  public void testEqualsAndHashCode() {
    LoginDTO loginDTO1 = new LoginDTO("jme@nucleusteq.com", "password123@Jme");
    LoginDTO loginDTO2 = new LoginDTO("jme@nucleusteq.com", "password123@Jme");
    LoginDTO loginDTO3 = new LoginDTO("jmejagat@nucleusteq.com", "password456");

    assertEquals(loginDTO1, loginDTO2);
    assertNotEquals(loginDTO1, loginDTO3);

    assertEquals(loginDTO1.hashCode(), loginDTO2.hashCode());
    assertNotEquals(loginDTO1.hashCode(), loginDTO3.hashCode());
  }

  @Test
  public void testToString() {
    LoginDTO loginDTO = new LoginDTO("jagat@nucleusteq.com", "password123@Jme");
    String expectedToString = "LoginDTO [email=jagat@nucleusteq.com, password=password123@Jme]";
    assertEquals(expectedToString, loginDTO.toString());
  }

  @Test
  void testEquals_SameObject() {
      LoginDTO loginDTO = new LoginDTO("jmejagat@nucleusteq.com", "password123");
      assertTrue(loginDTO.equals(loginDTO));
  }

  @Test
  void testEquals_NullObject() {
      LoginDTO loginDTO = new LoginDTO("jmejagat@nucleusteq.com", "password@123");
      assertFalse(loginDTO.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
      LoginDTO loginDTO = new LoginDTO("jmejagat@nucleusteq.com", "jagat@123");
      assertFalse(loginDTO.equals("false_Object"));
  }

  @Test
  void testEquals_EqualObjects() {
      LoginDTO loginDTO1 = new LoginDTO("jmejagat@nucleusteq.com", "passwordR");
      LoginDTO loginDTO2 = new LoginDTO("jmejagat@nucleusteq.com", "passwordR");
      assertTrue(loginDTO1.equals(loginDTO2));
  }

  @Test
  void testEquals_UnequalObjects() {
      LoginDTO loginDTO1 = new LoginDTO("jmejagat@nucleusteq.com", "password");
      LoginDTO loginDTO2 = new LoginDTO("jmejagat@nucleusteq.com", "false_password");
      assertFalse(loginDTO1.equals(loginDTO2));
  }

}

