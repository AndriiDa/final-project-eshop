package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = false)
public class PropertyValueDto extends AbstractDto {
  @Id
  private Long id;

  private Long propertyId;

  private String name;

  private String description;
}
