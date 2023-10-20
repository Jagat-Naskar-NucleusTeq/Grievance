package com.feedback.payloads.user_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginDtoOutTest {
    @Test
    void testGettersAndSetters() {
        LoginDtoOut loginDtoOut = new LoginDtoOut();
        
        // Set values using setters
        loginDtoOut.setName("Jagat Naskar");
        loginDtoOut.setUserName("jme@nucleusteq.com");
        loginDtoOut.setPassword("password123");
        loginDtoOut.setUserType("admin");
        loginDtoOut.setFinalPassword("true");
        loginDtoOut.setDepartmentName("HR");
        
        // Check if values are set correctly
        assertEquals("Jagat Naskar", loginDtoOut.getName());
        assertEquals("jme@nucleusteq.com", loginDtoOut.getUserName());
        assertEquals("password123", loginDtoOut.getPassword());
        assertEquals("admin", loginDtoOut.getUserType());
        assertEquals("true", loginDtoOut.getFinalPassword());
        assertEquals("HR", loginDtoOut.getDepartmentName());
    }

    @Test
    void testEqualsAndHashCode() {
        LoginDtoOut loginDtoOut1 = new LoginDtoOut("Jagat Naskar", "jme@nucleusteq.com", "password123", "admin", "true", "HR");
        LoginDtoOut loginDtoOut2 = new LoginDtoOut("Jagat Naskar", "jme@nucleusteq.com", "password123", "admin", "true", "HR");

        // Check if the objects are equal
        assertTrue(loginDtoOut1.equals(loginDtoOut2) && loginDtoOut2.equals(loginDtoOut1));
        assertEquals(loginDtoOut1.hashCode(), loginDtoOut2.hashCode());

        // Change one field and check again
        loginDtoOut2.setDepartmentName("Finance");
        assertFalse(loginDtoOut1.equals(loginDtoOut2) || loginDtoOut2.equals(loginDtoOut1));
        assertNotEquals(loginDtoOut1.hashCode(), loginDtoOut2.hashCode());
    }

    @Test
    void testConstructor() {
        LoginDtoOut loginDtoOut = new LoginDtoOut("Jagat Naskar", "jme@nucleusteq.com", "password123", "admin", "true", "HR");

        // Check if values are set correctly
        assertEquals("Jagat Naskar", loginDtoOut.getName());
        assertEquals("jme@nucleusteq.com", loginDtoOut.getUserName());
        assertEquals("password123", loginDtoOut.getPassword());
        assertEquals("admin", loginDtoOut.getUserType());
        assertEquals("true", loginDtoOut.getFinalPassword());
        assertEquals("HR", loginDtoOut.getDepartmentName());
    }

    @Test
    void testToString() {
        LoginDtoOut loginDtoOut = new LoginDtoOut("Jagat Naskar", "jme@nucleusteq.com", "password123", "admin", "true", "HR");
        String expectedToString = "LoginDtoOut [" +
                "name=Jagat Naskar, " +
                "userName=jme@nucleusteq.com, " +
                "password=password123, " +
                "userType=admin, " +
                "finalPassword=true, " +
                "departmentName=HR]";

        assertEquals(expectedToString, loginDtoOut.toString());
    }
}
