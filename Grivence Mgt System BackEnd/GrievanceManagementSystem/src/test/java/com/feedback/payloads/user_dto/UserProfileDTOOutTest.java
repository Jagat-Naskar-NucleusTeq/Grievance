package com.feedback.payloads.user_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserProfileDtoOutTest {

	@Test
    public void testGettersAndSetters() {
        UserProfileDtoOut userProfile = new UserProfileDtoOut();
        userProfile.setName("Jagat Naskar");
        userProfile.setUserName("jagatnaskar@nucleusteq.com");
        userProfile.setPassword("password123");
        userProfile.setUserType("Admin");
        userProfile.setDepartmentName("IT");

        assertEquals("Jagat Naskar", userProfile.getName());
        assertEquals("jagatnaskar@nucleusteq.com", userProfile.getUserName());
        assertEquals("password123", userProfile.getPassword());
        assertEquals("Admin", userProfile.getUserType());
        assertEquals("IT", userProfile.getDepartmentName());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserProfileDtoOut userProfile1 = new UserProfileDtoOut("Jagat Naskar", "jagatnaskar@nucleusteq.com", "password123", "Admin", "IT");
        UserProfileDtoOut userProfile2 = new UserProfileDtoOut("Jagat Naskar", "jagatnaskar@nucleusteq.com", "password123", "Admin", "IT");

        assertTrue(userProfile1.equals(userProfile2));
        assertTrue(userProfile2.equals(userProfile1));
        assertEquals(userProfile1.hashCode(), userProfile2.hashCode());

        userProfile2.setDepartmentName("Finance");

        assertFalse(userProfile1.equals(userProfile2));
        assertFalse(userProfile2.equals(userProfile1));
        assertNotEquals(userProfile1.hashCode(), userProfile2.hashCode());
    }

    @Test
    public void testConstructor() {
        UserProfileDtoOut userProfile = new UserProfileDtoOut("Jagat Naskar", "jagatnaskar@nucleusteq.com", "password123", "Admin", "IT");

        assertEquals("Jagat Naskar", userProfile.getName());
        assertEquals("jagatnaskar@nucleusteq.com", userProfile.getUserName());
        assertEquals("password123", userProfile.getPassword());
        assertEquals("Admin", userProfile.getUserType());
        assertEquals("IT", userProfile.getDepartmentName());
    }

    @Test
    public void testFieldConstructor() {
        UserProfileDtoOut userProfile = new UserProfileDtoOut("Jagat Naskar", "jagatnaskar@nucleusteq.com", "password123", "Admin", "IT");

        assertEquals("Jagat Naskar", userProfile.getName());
        assertEquals("jagatnaskar@nucleusteq.com", userProfile.getUserName());
        assertEquals("password123", userProfile.getPassword());
        assertEquals("Admin", userProfile.getUserType());
        assertEquals("IT", userProfile.getDepartmentName());
    }

    @Test
    void testToString() {
        UserProfileDtoOut userProfile = new UserProfileDtoOut(
            "Jagat Naskar", "jme@nucleusteq.com", "password123", "Admin", "IT Department");

        String expectedString = "UserProfileDTOout [name=Jagat Naskar, userName=jme@nucleusteq.com, password=password123, userType=Admin, departmentName=IT Department]";

        String actualString = userProfile.toString();

        assertEquals(expectedString, actualString);
    }
}
