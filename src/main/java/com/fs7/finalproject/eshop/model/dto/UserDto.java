package com.fs7.finalproject.eshop.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fs7.finalproject.eshop.model.Address;
import com.fs7.finalproject.eshop.model.Gender;
import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.convert.GenderConverter;
import com.fs7.finalproject.eshop.model.convert.RoleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDto extends AbstractDto{
  @Id
  private Long id;

  private String firstName;

  private String lastName;

  private String middleName;

  private String email;

  private String loginName;

  private String loginPassword;

  private String phoneNumber;

  @Convert(converter = GenderConverter.class)
  private Gender gender;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date birthDate;

  private Long addressId;

  private Boolean emailVerified;

  private String verificationCode;

  @Convert(converter = RoleConverter.class)
  private Role role;

  private Boolean isActive;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private Date crTime;
}