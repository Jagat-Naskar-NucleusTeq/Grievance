package com.feedback.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * ERole converter class.
 *
 * @author jagat
 *
 */
@Converter(autoApply = true)
public class ERoleConverter implements AttributeConverter<ERole, String> {

  /**
   * convertToDatabaseColumn.
   * converting eRole to String.
   */
  @Override
  public String convertToDatabaseColumn(final ERole role) {
    if (role == null) {
      return null;
    }
    return role.toString();
  }

  /**
   * convertToEntityAttribute.
   * converting String to eRole.
   */
  @Override
  public ERole convertToEntityAttribute(final String role) {
    if (role == null) {
      return null;
    }
    return ERole.valueOf(role);
  }
}
