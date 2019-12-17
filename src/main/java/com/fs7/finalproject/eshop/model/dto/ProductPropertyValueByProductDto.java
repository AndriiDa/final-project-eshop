package com.fs7.finalproject.eshop.model.dto;

import com.fs7.finalproject.eshop.model.PropertyValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductPropertyValueByProductDto extends AbstractDto{
  @Id
  private Long id;

  private Long productId;

  private Long propertyValueId;

  private String propertyName;

  private String propertyValueName;

}
