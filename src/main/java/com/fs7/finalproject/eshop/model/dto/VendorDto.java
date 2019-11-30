package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class VendorDto {
  @Id
  private Long id;

  private String name;

  private String notes;

  private Boolean isActive;

  private Date crTime;

  private Long crUserId;

  private Date lmTime;

  private Long lmUserId;
}