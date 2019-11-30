package com.fs7.finalproject.eshop.model.dto;

import com.fs7.finalproject.eshop.model.PropertyValue;
import lombok.Data;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Data
public class PropertyDto {
  @Id
  private Long id;

  private String name;

  private String description;

  private Set<PropertyValue> values = new HashSet<>();
}
