package com.fs7.finalproject.eshop.payloads;

import lombok.Data;

import java.time.Instant;

@Data
public class UserProfile {
  private Long id;
  private String loginName;
  private String firstName;
  private String lastName;
  private Instant joinedAt;

  public UserProfile(Long id, String loginName, String firstName, String lastName, Instant joinedAt) {
    this.id = id;
    this.loginName = loginName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.joinedAt = joinedAt;
  }
}
