package com.fs7.finalproject.eshop.model;

public enum Role {
  ADMIN("A"), CUSTOMER("C"), GUEST("G");

  private final String shortName;

  Role(String shortName) {
    this.shortName = shortName;
  }

  public String getShortName() {
    return this.shortName;
  }

  public static Role fromShortName(String shortName) {
    switch (shortName) {
      case "A":
        return Role.ADMIN;
      case "C":
        return Role.CUSTOMER;
      case "G":
        return Role.GUEST;
      default:
        throw new IllegalArgumentException("ShortName [" + shortName + "] not supported");
    }
  }
}
