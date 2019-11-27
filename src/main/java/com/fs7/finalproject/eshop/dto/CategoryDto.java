package com.fs7.finalproject.eshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class CategoryDto {
  @Id
  private Long id;

  @JsonIgnore
  private Long categoryId;

  private boolean isGroup;

  private String code;

  private String name;

  private String imgUrl;

  private String description;

  private boolean isActive;

  private Date crTime;

  private Long crUserId;

  private Date lmTime;

  private Long lmUserId;
}
