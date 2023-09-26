package com.feedback.payloads.user_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserProfileDTOoutTest {

	@Test
    public void testGettersAndSetters() {
        UserProfileDTOout userProfile = new UserProfileDTOout();
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
        UserProfileDTOout userProfile1 = new UserProfileDTOout("Jagat Naskar", "jagatnaskar@nucleusteq.com", "password123", "Admin", "IT");
        UserProfileDTOout userProfile2 = new UserProfileDTOout("Jagat Naskar", "jagatnaskar@nucleusteq.com", "password123", "Admin", "IT");

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
        UserProfileDTOout userProfile = new UserProfileDTOout("Jagat Naskar", "jagatnaskar@nucleusteq.com", "password123", "Admin", "IT");

        assertEquals("Jagat Naskar", userProfile.getName());
        assertEquals("jagatnaskar@nucleusteq.com", userProfile.getUserName());
        assertEquals("password123", userProfile.getPassword());
        assertEquals("Admin", userProfile.getUserType());
        assertEquals("IT", userProfile.getDepartmentName());
    }

    @Test
    public void testFieldConstructor() {
        UserProfileDTOout userProfile = new UserProfileDTOout("Jagat Naskar", "jagatnaskar@nucleusteq.com", "password123", "Admin", "IT");

        assertEquals("Jagat Naskar", userProfile.getName());
        assertEquals("jagatnaskar@nucleusteq.com", userProfile.getUserName());
        assertEquals("password123", userProfile.getPassword());
        assertEquals("Admin", userProfile.getUserType());
        assertEquals("IT", userProfile.getDepartmentName());
    }

}
