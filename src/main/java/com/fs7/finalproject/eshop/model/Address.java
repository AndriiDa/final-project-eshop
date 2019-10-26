package com.fs7.finalproject.eshop.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESSES")
@Data
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "COUNTRY", nullable = true, length = 100)
  private String country;

  @Column(name = "STATE", nullable = true, length = 100)
  private String state;

  @Column(name = "CITY", nullable = true, length = 100)
  private String city;

  @Column(name = "STREET", nullable = true, length = 100)
  private String street;

  @Column(name = "ZIP_CODE", nullable = true, length = 8)
  private String zipCode;

  @Column(name = "ADDRESS_LINE", nullable = true, length = 250)
  private String addressLine;
}
