package com.fs7.finalproject.eshop.model;

import com.fs7.finalproject.eshop.model.convert.GenderConverter;
import com.fs7.finalproject.eshop.model.convert.RoleConverter;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "LOGIN_NAME"),
        @UniqueConstraint(columnNames = "EMAIL")})
@Data
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", updatable = false)
  private Long id;

  //  @Version
  //  @Column(name = "version")
  //  private int version = 0;

  @Column(name = "FIRST_NAME", nullable = false, length = 100)
  private String firstName;

  @Column(name = "LAST_NAME", nullable = false, length = 100)
  private String lastName;

  @Column(name = "MIDDLE_NAME", length = 100)
  private String middleName;

  @Email
  @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
  private String email;

  @Column(name = "LOGIN_NAME", unique = true, nullable = false, length = 50)
  private String loginName;

  @Column(name = "LOGIN_PASSWORD", length = 50)
  private String loginPassword;

  @Column(name = "PHONE_NUMBER", length = 50)
  private String phoneNumber;

  @Convert(converter = GenderConverter.class)
  private Gender gender;

  @Temporal(TemporalType.DATE)
  private Date birthDate;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
  private Address address;

  private boolean emailVerified;

  @Column(name = "VERIFICATION_CODE", length = 20)
  private String verificationCode;

  @Convert(converter = RoleConverter.class)
  @Column(nullable = false)
  private Role role;

  private boolean isActive;
}