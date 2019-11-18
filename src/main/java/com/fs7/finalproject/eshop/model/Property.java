package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "PROPERTIES", uniqueConstraints = {
        @UniqueConstraint(name = "IX_PROPERTIES_NAME", columnNames = "NAME")})
public class Property {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<PropertyValue> values = new HashSet<>();
}
