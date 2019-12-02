package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;
import javax.persistence.Id;
import java.util.Set;

@Data
public class PropertyValueDto extends AbstractDto {
  @Id
  private Long id;

  private Long propertyId;

  private String name;

  private String description;
}
