package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = false)
public class BrandDto extends AbstractDtoWithAudit {
  @Id
  private Long id;

  private String name;

  private String notes;

  private Boolean isActive;

  private Long crUserId;

  private Long lmUserId;
}