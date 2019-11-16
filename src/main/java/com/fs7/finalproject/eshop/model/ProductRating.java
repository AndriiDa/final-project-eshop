package com.fs7.finalproject.eshop.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@Table(name = "PRODUCT_RATING", uniqueConstraints = {
        @UniqueConstraint(name = "IX_PRODUCT_RATING_USER_ID_PRODUCT_ID", columnNames = {"USER_ID", "PRODUCT_ID"})})
public class ProductRating {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PRODUCT_RATING_USERS_USER_ID"))
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PRODUCT_RATING_PRODUCTS_PRODUCT_ID"))
  private Product product;

  @Column(name = "VALUE", nullable = false)
  private int value;
}
