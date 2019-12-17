package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductPropertyValueDto extends AbstractDto{
  @Id
  private Long id;

  private Long productId;

  private Long propertyValueId;
}
