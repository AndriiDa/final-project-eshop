package com.fs7.finalproject.eshop.payloads;

import lombok.Data;

@Data
public class UserSummary {
  private Long id;
  private String loginName;
  private String email;

  public UserSummary(Long id, String loginName, String email) {
    this.id = id;
    this.loginName = loginName;
    this.email = email;
  }
}
