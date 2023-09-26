package com.feedback.payloads.user_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class getAllUsersDTOoutTest {

    @Test
    public void testGetSetName() {
        getAllUsersDTOout dto = new getAllUsersDTOout();
        dto.setName("Jagat Naskar");
        assertEquals("Jagat Naskar", dto.getName());
    }

    @Test
    public void testGetSetUserName() {
        getAllUsersDTOout dto = new getAllUsersDTOout();
        dto.setUserName("Ramzan");
        assertEquals("Ramzan", dto.getUserName());
    }

    @Test
    public void testGetSetUserType() {
        getAllUsersDTOout dto = new getAllUsersDTOout();
        dto.setUserType("admin");
        assertEquals("admin", dto.getUserType());
    }

    @Test
    public void testGetSetDepartmentName() {
        getAllUsersDTOout dto = new getAllUsersDTOout();
        dto.setDepartmentName("IT");
        assertEquals("IT", dto.getDepartmentName());
    }

    @Test
    public void testConstructorAndGetters() {
        getAllUsersDTOout dto = new getAllUsersDTOout(1, "Jagat", "jagat123", "User", "HR");
        assertEquals("Jagat", dto.getName());
        assertEquals("jagat123", dto.getUserName());
        assertEquals("User", dto.getUserType());
        assertEquals("HR", dto.getDepartmentName());
    }

    @Test
    public void testToString() {
        getAllUsersDTOout dto = new getAllUsersDTOout(2, "Karobi", "karobi123", "admin", "Finance");
        String expectedToString = "getAllUsersDTOout [id=2, name=Karobi, userName=karobi123, userType=admin, departmentName=Finance]";
        assertEquals(expectedToString, dto.toString());
    }

    @Test
    public void testHashCode() {
        getAllUsersDTOout dto1 = new getAllUsersDTOout(3, "Alice", "alice123", "User", "HR");
        getAllUsersDTOout dto2 = new getAllUsersDTOout(3, "Alice", "alice123", "User", "HR");

        assertEquals(dto1.hashCode(), dto2.hashCode());

        // Changing a field should change the hash code
        dto2.setName("Bob");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testFieldConstructor() {
        getAllUsersDTOout dto = new getAllUsersDTOout(4, "Charlie", "charlie789", "Employee", "Sales");
        assertEquals(4, dto.getId());
        assertEquals("Charlie", dto.getName());
        assertEquals("charlie789", dto.getUserName());
        assertEquals("Employee", dto.getUserType());
        assertEquals("Sales", dto.getDepartmentName());
    }

    @Test
    public void testNoArgsConstructor() {
        getAllUsersDTOout dto = new getAllUsersDTOout();
        assertEquals(0, dto.getId());
        assertEquals(null, dto.getName());
        assertEquals(null, dto.getUserName());
        assertEquals(null, dto.getUserType());
        assertEquals(null, dto.getDepartmentName());
    }

}
