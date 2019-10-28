package com.fs7.finalproject.eshop.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY_PROPERTY_VALUES")
@Data
@Builder
public class CaregoryPropertyValue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "PROPERTY_VALUE_ID", referencedColumnName = "ID", nullable = false)
  private PropertyValue propertyValue;

  @Column(name = "SEQUENCE", nullable = false)
  private int sequence;
}
