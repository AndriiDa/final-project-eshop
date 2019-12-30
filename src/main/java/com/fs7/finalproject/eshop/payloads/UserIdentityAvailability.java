package com.fs7.finalproject.eshop.payloads;

import lombok.Data;

@Data
public class UserIdentityAvailability {
  private Boolean isAvailable;

  public UserIdentityAvailability(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
}
