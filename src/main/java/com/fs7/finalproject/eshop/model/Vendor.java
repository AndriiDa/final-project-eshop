package com.fs7.finalproject.eshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "VENDORS")
@Data
@NoArgsConstructor
public class Vendor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @OneToMany
  private List<Product> products;

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

  @Column(name = "LM_USER_ID")
  private Long lmUserId;


}
