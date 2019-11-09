package com.fs7.finalproject.eshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "BRANDS")
@Data
@NoArgsConstructor
public class Brand {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "NOTES")
  private String notes;

  @Column(name = "IS_ACTIVE")
  private Boolean isActive;

  @Column(name = "CR_TIME")
  private Date crTime;

  @Column(name = "CR_USER_ID")
  private Long crUserId;

  @Column(name = "LM_TIME")
  private Date lmTime;

  @Column(name = "LM_USER_TIME")
  private Long lmUserId;
}
