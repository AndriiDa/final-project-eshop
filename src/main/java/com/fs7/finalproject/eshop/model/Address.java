package com.fs7.finalproject.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@Table(name = "ADDRESSES")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
