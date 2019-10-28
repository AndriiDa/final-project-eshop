package com.fs7.finalproject.eshop.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_RATING")
@Data
public class ProductRating {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "USER_ID", nullable = false)
  private Long userId;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @Column(name = "VALUE", nullable = false)
  private int value;

}
