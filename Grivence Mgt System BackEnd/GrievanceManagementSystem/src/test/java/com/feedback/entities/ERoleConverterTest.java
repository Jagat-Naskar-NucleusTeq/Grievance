package com.feedback.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.feedback.mapper.EroleConverter;
import com.feedback.mapper.EstatusConverter;

class ERoleConverterTest {
    @Test
    void testConvertToDatabaseColumn() {
        EroleConverter converter = new EroleConverter();
        ERole role = ERole.admin;
        String expectedString = "admin";
        String result = converter.convertToDatabaseColumn(role);
        assertEquals(expectedString, result);
    }

    @Test
    void testConvertToEntityAttribute() {
        EroleConverter converter = new EroleConverter();
        String roleString = "member";
        ERole result = converter.convertToEntityAttribute(roleString);
        assertEquals(ERole.member, result);
    }

    @Test
    void testConvertToDatabaseColumnWithNull() {
        EroleConverter converter = new EroleConverter();
        ERole role = null;

        String result = converter.convertToDatabaseColumn(role);

        assertNull(result);
    }

    @Test
    void testConvertToEntityAttributeWithNull() {
        EroleConverter converter = new EroleConverter();
        String roleString = null;
        ERole result = converter.convertToEntityAttribute(roleString);
        assertNull(result);
    }

    @Test
    public void testConstructor() {
        EstatusConverter converter = new EstatusConverter();
        assertNotNull(converter);
    }

    @Test
    public void testConvertStringToEStatus() {

        assertEquals(Estatus.Open, EstatusConverter.convertStringToEStatus("open"));
        assertEquals(Estatus.Being_Addressed, EstatusConverter.convertStringToEStatus("being_addressed"));
        assertEquals(Estatus.Resolved, EstatusConverter.convertStringToEStatus("resolved"));

        assertEquals(null, EstatusConverter.convertStringToEStatus("invalid_status"));
    }
}
