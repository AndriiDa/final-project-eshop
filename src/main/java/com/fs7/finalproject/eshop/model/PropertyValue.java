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
@Table(name = "PROPERTY_VALUES",
        uniqueConstraints = {
                @UniqueConstraint(name = "IX_PROPERTY_VALUES_PROPERTY_ID_NAME", columnNames = {"PROPERTY_ID", "NAME"})})
public class PropertyValue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PROPERTY_VALUES_PROPERTIES_PROPERTY_ID"))
  private Property property;

  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;
}
