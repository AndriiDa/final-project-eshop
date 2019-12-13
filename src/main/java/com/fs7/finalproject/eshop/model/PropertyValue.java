package com.fs7.finalproject.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.CascadeType;

@Entity
@Table(name = "PROPERTY_VALUES",
    uniqueConstraints = {
        @UniqueConstraint(name = "IX_PROPERTY_VALUES_PROPERTY_ID_NAME", columnNames = {"PROPERTY_ID", "NAME"})})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PropertyValue extends AbstractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH
      })
  @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_PROPERTY_VALUES_PROPERTIES_PROPERTY_ID"))
  private Property property;

  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;

}