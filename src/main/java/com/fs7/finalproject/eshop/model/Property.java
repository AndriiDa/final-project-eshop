package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;

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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROPERTIES", uniqueConstraints = {
        @UniqueConstraint(name = "IX_PROPERTIES_NAME", columnNames = "NAME")})
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Property extends AbstractEntity {
  @Id

  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @JsonManagedReference
  @OneToMany(mappedBy = "property", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<PropertyValue> propertyValues = new ArrayList<>();

}