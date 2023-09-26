package com.feedback.payloads.user_dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordChangeDTOinTest {

  @Test
  public void testGettersAndSetters() {
    PasswordChangeDTOin passwordChangeDTO = new PasswordChangeDTOin();
    passwordChangeDTO.setUserName("Jagat Naskar");
    passwordChangeDTO.setOldPassword("oldPasswordNucleusteq");
    passwordChangeDTO.setNewPassword("newPassword");
    passwordChangeDTO.setConfirmNewPassword("newPassword");

    assertEquals("Jagat Naskar", passwordChangeDTO.getUserName());
    assertEquals("oldPasswordNucleusteq", passwordChangeDTO.getOldPassword());
    assertEquals("newPassword", passwordChangeDTO.getNewPassword());
    assertEquals("newPassword", passwordChangeDTO.getConfirmNewPassword());
  }

  @Test
  public void testEqualsAndHashCode() {
    PasswordChangeDTOin passwordChangeDTO1 = new PasswordChangeDTOin("Jagat Naskar", 
        "oldPasswordNucleusteq", 
        "newPassword", 
        "newPassword");
    PasswordChangeDTOin passwordChangeDTO2 = new PasswordChangeDTOin("Jagat Naskar", 
        "oldPasswordNucleusteq", 
        "newPassword", 
        "newPassword");

    assertTrue(passwordChangeDTO1.equals(passwordChangeDTO2));
    assertTrue(passwordChangeDTO2.equals(passwordChangeDTO1));
    assertEquals(passwordChangeDTO1.hashCode(), passwordChangeDTO2.hashCode());

    passwordChangeDTO2.setConfirmNewPassword("differentPassword");

    assertFalse(passwordChangeDTO1.equals(passwordChangeDTO2));
    assertFalse(passwordChangeDTO2.equals(passwordChangeDTO1));
    assertNotEquals(passwordChangeDTO1.hashCode(), passwordChangeDTO2.hashCode());
  }

  @Test
  public void testConstructor() {
    PasswordChangeDTOin passwordChangeDTO = new PasswordChangeDTOin(
      "Jagat Naskar", 
      "oldPasswordNucleusteq", 
      "newPassword", 
      "newPassword");

    assertEquals("Jagat Naskar", passwordChangeDTO.getUserName());
    assertEquals("oldPasswordNucleusteq", passwordChangeDTO.getOldPassword());
    assertEquals("newPassword", passwordChangeDTO.getNewPassword());
    assertEquals("newPassword", passwordChangeDTO.getConfirmNewPassword());
  }

  @Test
  public void testFieldConstructor() {
    PasswordChangeDTOin passwordChangeDTO = new PasswordChangeDTOin(
        "Jagat Naskar", 
        "oldPasswordNucleusteq", 
        "newPassword", 
        "newPassword");

    assertEquals("Jagat Naskar", passwordChangeDTO.getUserName());
    assertEquals("oldPasswordNucleusteq", passwordChangeDTO.getOldPassword());
    assertEquals("newPassword", passwordChangeDTO.getNewPassword());
    assertEquals("newPassword", passwordChangeDTO.getConfirmNewPassword());
  }

}
