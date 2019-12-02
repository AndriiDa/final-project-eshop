package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class PropertyDto extends AbstractDto {
  @Id
  private Long id;

  private String name;

  private String description;

  private List<PropertyValueDto> propertyValues;
}
