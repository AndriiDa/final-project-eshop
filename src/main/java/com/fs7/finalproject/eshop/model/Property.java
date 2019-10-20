package com.fs7.finalproject.eshop.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PROPERTIES", uniqueConstraints = {
        @UniqueConstraint(columnNames = "NAME")})
@Data
public class Property {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;
}
