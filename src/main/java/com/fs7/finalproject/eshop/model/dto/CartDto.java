package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = false)
public class CartDto extends AbstractDto {
  @Id
  private Long id;

  private Long userId;

  private Long productId;

  private int quantity;
}