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
public class Brand extends BaseEntity{

  @Column(name = "name")
  private String name;

  @Column(name = "notes")
  private String notes;

  @Column(name = "is_active")
  private Boolean is_active;

  @Column(name = "cr_time")
  private Date cr_time;

  @Column(name = "cr_user_id")
  private Long cr_user_id;

  @Column(name = "lm_time")
  private Date lm_time;

  @Column(name = "lm_user_id")
  private Long lm_user_id;
}
