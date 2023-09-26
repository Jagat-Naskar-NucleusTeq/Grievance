package com.feedback.payloads.user_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.feedback.entities.ERole;

class AddUserDtoTest {

  private AddUserDto addUserDto;

  @BeforeEach
  public void setUp() {
    addUserDto = new AddUserDto("Jagat", "jme@nucleusteq.com", "password123", ERole.admin, "HR");
  }

  @Test
  public void testGettersAndSetters() {
    // Test getters
    assertEquals("Jagat", addUserDto.getName());
    assertEquals("jme@nucleusteq.com", addUserDto.getUserName());
    assertEquals("password123", addUserDto.getPassword());
    assertEquals(ERole.admin, addUserDto.getUserType());
    assertEquals("HR", addUserDto.getDepartmentName());

    // Test setters
    addUserDto.setName("Karobi");
    addUserDto.setUserName("karobi@nucleusteq.com");
    addUserDto.setPassword("newpassword");
    addUserDto.setUserType(ERole.member);
    addUserDto.setDepartmentName("Sales");

    assertEquals("Karobi", addUserDto.getName());
    assertEquals("karobi@nucleusteq.com", addUserDto.getUserName());
    assertEquals("newpassword", addUserDto.getPassword());
    assertEquals(ERole.member, addUserDto.getUserType());
    assertEquals("Sales", addUserDto.getDepartmentName());
  }

  @Test
  public void testToString() {
    String expectedToString = "AddUser [name=Jagat, "
      + "userName=jme@nucleusteq.com, "
      + "password=password123, "
      + "userType=admin, "
      + "department=HR]";
    assertEquals(expectedToString, addUserDto.toString());
  }

  @Test
  public void testHashCode() {
    AddUserDto anotherUserDto = new AddUserDto("Jagat", 
      "jme@nucleusteq.com", 
      "password123", 
      ERole.admin, 
      "HR");
    assertEquals(addUserDto.hashCode(), anotherUserDto.hashCode());
  }

  @Test
  public void testEquals() {
    AddUserDto sameUserDto = new AddUserDto("Jagat", 
      "jme@nucleusteq.com", 
      "password123", 
      ERole.admin, 
      "HR");
    AddUserDto differentUserDto = new AddUserDto("Karobi", 
      "karobi@nucleusteq.com", 
      "newpassword", 
      ERole.member, 
      "Sales");

    // Test equality with another instance having the same values
    assertTrue(addUserDto.equals(sameUserDto));

    // Test inequality with another instance having different values
    assertFalse(addUserDto.equals(differentUserDto));

    // Test inequality with a null object
    assertFalse(addUserDto.equals(null));

    // Test inequality with a different class object
    assertFalse(addUserDto.equals("NotAUserDto"));
  }

}
