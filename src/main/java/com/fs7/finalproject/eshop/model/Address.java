package com.fs7.finalproject.eshop.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
@Data
@Builder
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Column(nullable = false)
  private String addressLine;
}
