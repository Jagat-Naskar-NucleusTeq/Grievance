package com.feedback.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.feedback.mapper.ERoleConverter;
import com.feedback.mapper.EStatusConverter;

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

    @Test
    public void testConstructor() {
        EStatusConverter converter = new EStatusConverter();
        assertNotNull(converter);
    }

    @Test
    public void testConvertStringToEStatus() {

        assertEquals(EStatus.Open, EStatusConverter.convertStringToEStatus("open"));
        assertEquals(EStatus.Being_Addressed, EStatusConverter.convertStringToEStatus("being_addressed"));
        assertEquals(EStatus.Resolved, EStatusConverter.convertStringToEStatus("resolved"));

        assertEquals(null, EStatusConverter.convertStringToEStatus("invalid_status"));
    }
}
