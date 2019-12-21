package com.fs7.finalproject.eshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoryDto extends AbstractDtoWithAudit{
  @Id
  private Long id;

  private Long parentCategoryId;

  private Boolean isGroup;

  private String code;

  private String name;

  private String description;

  private String imgUrl;

  private Boolean isActive;

  private Long crUserId;

  private Long lmUserId;
}
