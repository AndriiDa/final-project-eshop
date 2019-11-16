package com.fs7.finalproject.eshop.model;

import lombok.Builder;
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
@Builder
@Table(name = "CATEGORY_PROPERTY_VALUES", uniqueConstraints = {
        @UniqueConstraint(name = "IX_CATEGORY_CATEGORY_ID_PROPERTY_VALUE_ID",
                columnNames = {"CATEGORY_ID", "PROPERTY_VALUE_ID"})})
public class CategoryPropertyValue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_CATEGORY_PROPERTY_VALUES_CATEGORIES_CATEGORY_ID"))
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "PROPERTY_VALUE_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_CATEGORY_PROPERTY_VALUES_CATEGORIES_PROPERTY_VALUE_ID"))
  private PropertyValue propertyValue;

  @Column(name = "SEQUENCE", nullable = false)
  private int sequence;
}
