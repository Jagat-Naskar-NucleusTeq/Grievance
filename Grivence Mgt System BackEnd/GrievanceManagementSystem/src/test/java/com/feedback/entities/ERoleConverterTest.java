package com.feedback.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ERoleConverterTest {
    @Test
    void testConvertToDatabaseColumn() {
        ERoleConverter converter = new ERoleConverter();
        ERole role = ERole.admin;
        String expectedString = "admin";
        String result = converter.convertToDatabaseColumn(role);
        assertEquals(expectedString, result);
    }

    @Test
    void testConvertToEntityAttribute() {
        ERoleConverter converter = new ERoleConverter();
        String roleString = "member";
        ERole result = converter.convertToEntityAttribute(roleString);
        assertEquals(ERole.member, result);
    }

    @Test
    void testConvertToDatabaseColumnWithNull() {
        ERoleConverter converter = new ERoleConverter();
        ERole role = null;

        String result = converter.convertToDatabaseColumn(role);

        assertNull(result);
    }

    @Test
    void testConvertToEntityAttributeWithNull() {
        ERoleConverter converter = new ERoleConverter();
        String roleString = null;
        ERole result = converter.convertToEntityAttribute(roleString);
        assertNull(result);
    }
    
}
