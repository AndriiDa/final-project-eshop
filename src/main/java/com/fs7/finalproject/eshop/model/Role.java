package com.fs7.finalproject.eshop.model;

public enum Role {
  A("Admin"), C("Customer"), G("Guest");

  private final String role;

  Role(String gender) {
    this.role = gender;
  }

  public String getRole() {
    return this.role;
  }
}
