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
     * @param resourceNamee the resourceName to set
     */
    public void setResourceName(final String resourceNamee) {
        this.resourceName = resourceNamee;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldNamee the fieldName to set
     */
    public void setFieldName(final String fieldNamee) {
        this.fieldName = fieldNamee;
    }

    /**
     * @return the fieldValue
     */
    public Long getFieldValue() {
        return fieldValue;
    }

    /**
     * @param fieldValuee the fieldValue to set
     */
    public void setFieldValue(final Long fieldValuee) {
        this.fieldValue = fieldValuee;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Resource not found field constructor.
     * @param resourceNamee
     * @param fieldNamee
     * @param fieldValuee
     */
    public ResourceNotFoundException(final String resourceNamee,
            final String fieldNamee, final long fieldValuee) {
        super(String.format("%s not found with %s : %s", resourceNamee,
                fieldNamee, fieldValuee));
        this.resourceName = resourceNamee;
        this.fieldName = fieldNamee;
        this.fieldValue = fieldValuee;
    }
}
