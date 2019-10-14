package com.fs7.finalproject.eshop.model;

import com.fs7.finalproject.eshop.model.convert.GenderConverter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;
  private String middleName;
  @Email
  @Column(nullable = false)
  private String email;
  private String loginName;
  private String loginPassword;
  private String phoneNumber;
  //  @Enumerated(EnumType.STRING)
  @Convert( converter = GenderConverter.class )
  private Gender gender;
  @Temporal(TemporalType.DATE)
  private Date birthDate;
  //  private Address address;
  private boolean emailVerified;
  private String verificationCode;
  @Enumerated(EnumType.STRING)
  private Role role;

  private boolean isActive;
}