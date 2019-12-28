package com.fs7.finalproject.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESSES")
@EqualsAndHashCode(callSuper = false)
public class Address extends AbstractEntity{
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "COUNTRY", length = 100)
  private String country;

  @Column(name = "STATE", length = 100)
  private String state;

  @Column(name = "CITY", length = 100)
  private String city;

  @Column(name = "STREET", length = 100)
  private String street;

  @Column(name = "ZIP_CODE", length = 8)
  private String zipCode;

  @Column(name = "ADDRESS_LINE", nullable = false, length = 250)
  private String addressLine;
}
