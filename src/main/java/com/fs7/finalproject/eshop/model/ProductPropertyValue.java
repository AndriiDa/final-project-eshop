package com.fs7.finalproject.eshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name = "PRODUCT_PROPERTY_VALUES")
@Data
public class ProductPropertyValue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @Column(name = "PROPERTY_VALUE_ID", nullable = false)
  private Long productValueId;

}
