package com.fs7.finalproject.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "CARTS")
public class Cart extends AbstractEntity {
  @Column(updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_CARTS_USERS_USER_ID"))
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_CARTS_PRODUCTS_PRODUCT_ID"))
  private Product product;

  @Column(name = "QUANTITY", nullable = false)
  private int quantity;
}
