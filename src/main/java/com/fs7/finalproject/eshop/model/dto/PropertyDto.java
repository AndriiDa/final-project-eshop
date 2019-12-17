package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PropertyDto extends AbstractDto {
  @Id
  private Long id;

  private String name;

  private String description;

  private List<Long> propertyValuesIds = new ArrayList<>();

}
