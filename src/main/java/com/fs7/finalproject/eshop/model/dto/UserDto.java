package com.fs7.finalproject.eshop.model.dto;

import com.fs7.finalproject.eshop.model.Address;
import com.fs7.finalproject.eshop.model.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String middleName;
  private String email;
  private String loginName;
  private String phoneNumber;
  private Gender gender;
  private Date birthDate;
  private Address address;
  private boolean emailVerified;
  private String verificationCode;
}
