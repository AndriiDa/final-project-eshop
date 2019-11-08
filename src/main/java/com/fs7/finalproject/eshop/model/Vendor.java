package com.fs7.finalproject.eshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

  @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
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
