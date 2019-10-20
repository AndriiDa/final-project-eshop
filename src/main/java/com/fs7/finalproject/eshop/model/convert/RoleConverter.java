package com.fs7.finalproject.eshop.model.convert;

import com.fs7.finalproject.eshop.model.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {
  @Override
  public String convertToDatabaseColumn(Role role) {
    return role.getShortName();
  }

  @Override
  public Role convertToEntityAttribute(String dbData) {
    return Role.fromShortName(dbData);
  }
}
