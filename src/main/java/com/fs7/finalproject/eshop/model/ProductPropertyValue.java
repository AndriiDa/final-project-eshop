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
@Table(name = "PRODUCT_PROPERTY_VALUES", uniqueConstraints = {
        @UniqueConstraint(name = "IX_PRODUCT_PROPERTY_VALUES_PRODUCT_ID_PROPERTY_VALUE_ID",
                columnNames = {"PRODUCT_ID", "PROPERTY_VALUE_ID"})})
public class ProductPropertyValue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PRODUCT_PROPERTY_VALUES_PRODUCTS_PRODUCT_ID"))
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "PROPERTY_VALUE_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PRODUCT_PROPERTY_VALUES_PROPERTY_VALUES_PROPERTY_VALUE_ID"))
  private PropertyValue propertyValue;
}
