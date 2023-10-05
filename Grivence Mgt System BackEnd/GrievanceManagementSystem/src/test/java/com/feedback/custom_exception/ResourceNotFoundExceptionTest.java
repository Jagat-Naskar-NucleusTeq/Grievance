package com.feedback.custom_exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {
    @Test
    void testConstructor() {
        String resourceName = "Department";
        String fieldName = "deptId";
        long fieldValue = 123;
        ResourceNotFoundException exception = new ResourceNotFoundException(
                resourceName, fieldName, fieldValue);
        String expectedMessage = String.format("%s not found with %s : %s",
                resourceName, fieldName, fieldValue);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(resourceName, exception.getResourceName());
        assertEquals(fieldName, exception.getFieldName());
        assertEquals(fieldValue, exception.getFieldValue());
    }

    @Test
    public void testSetters() {
        ResourceNotFoundException exception = new ResourceNotFoundException(null, null, 0);

        exception.setResourceName("TestResource");
        exception.setFieldName("TestField");
        exception.setFieldValue(100L);

        assertEquals("TestResource", exception.getResourceName());
        assertEquals("TestField", exception.getFieldName());
        assertEquals(Long.valueOf(100L), exception.getFieldValue());
    }

    @Test
    public void testGetSerialversionuid() {
        long expectedSerialVersionUID = 1L;
        long actualSerialVersionUID = ResourceNotFoundException.getSerialversionuid();

        assertEquals(expectedSerialVersionUID, actualSerialVersionUID);
    }
}
