package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fs7.finalproject.eshop.model.convert.GenderConverter;
import com.fs7.finalproject.eshop.model.convert.RoleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS", uniqueConstraints = {
    @UniqueConstraint(name = "IX_USERS_LOGIN_NAME", columnNames = "LOGIN_NAME"),
    @UniqueConstraint(name = "IX_USERS_EMAIL", columnNames = "EMAIL")})
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity {

  public User(String firstName, String lastName,
              String email, Boolean emailVerified,
              String loginName, String loginPassword) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.emailVerified = emailVerified;
    this.loginName = loginName;
    this.loginPassword = loginPassword;
  }

  @Column(name = "ID", updatable = false)
  private Long id;

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

  @Column(name = "LOGIN_PASSWORD", length = 100)
  private String loginPassword;

  @Column(name = "PHONE_NUMBER", length = 50)
  private String phoneNumber;

  @Convert(converter = GenderConverter.class)
  private Gender gender;

  @Temporal(TemporalType.DATE)
  private Date birthDate;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID",
      foreignKey = @ForeignKey(name = "FK_USERS_ADDRESSES_ADDRESS_ID"))
  private Address address;

  @Column(columnDefinition = "boolean default false")
  private Boolean emailVerified;

  @Column(name = "VERIFICATION_CODE", length = 20)
  private String verificationCode;

  @Convert(converter = RoleConverter.class)
  @Column(nullable = false)
  private Role role;

  @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "boolean default false")
  @ColumnDefault("false")
  private Boolean isActive;

  @CreatedDate
  @Column(name = "CR_TIME", updatable = false, nullable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date crTime;
}