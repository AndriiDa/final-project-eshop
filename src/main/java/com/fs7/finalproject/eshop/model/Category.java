package com.fs7.finalproject.eshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CATEGORIES")
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Column(name = "PARENT_ID")
  private Long parentId;

  @Column(name = "IS_GROUP", nullable = false)
  private boolean isGroup;

  @Column(name = "CODE", length = 50)
  private String code;

  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  @Column(name = "IMG_URL", length = 100)
  private String imgUrl;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;

  @Column(name = "IS_ACTIVE", nullable = false)
  private boolean isActive;

  @Column(name = "CR_TIME", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date crTime;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false)
  private User crUser;

  @Column(name = "LM_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lmTime;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID")
  private User lmUser;
}
