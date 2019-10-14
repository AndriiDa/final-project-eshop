package com.fs7.finalproject.eshop.model.convert;

import com.fs7.finalproject.eshop.model.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

  @Override
  public String convertToDatabaseColumn(Gender gender) {
    return gender.getShortName();
  }

  @Override
  public Gender convertToEntityAttribute(String dbData) {
    return Gender.fromShortName(dbData);
  }

}