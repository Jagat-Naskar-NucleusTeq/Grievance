package com.feedback.custom_exception;

/**
 * ResourceNotFoundException class.
 * @author jagat.
 */
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * resourceName string variable.
     */
    private String resourceName;
    /**
     * FieldName string variable.
     */
    private String fieldName;
    /**
     * fieldValue long variable.
     */
    private Long fieldValue;

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return the fieldValue
     */
    public Long getFieldValue() {
        return fieldValue;
    }

    /**
     * @param fieldValue the fieldValue to set
     */
    public void setFieldValue(final Long fieldValue) {
        this.fieldValue = fieldValue;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Resource not found field constructor.
     * @param resourceName
     * @param fieldName
     * @param fieldValue
     */
    public ResourceNotFoundException(final String resourceName,
            final String fieldName, final long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName,
                fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
