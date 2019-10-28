package com.fs7.finalproject.eshop.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY_VALUES", uniqueConstraints = {@UniqueConstraint(columnNames = {"PROPERTY_ID", "NAME"})})
@Data
@Builder
public class PropertyValue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "ID", nullable = false, foreignKey = @ForeignKey(name = "FK_PROPERTY_VALUE_PROPERTY"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Property property;

  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;
}
