package com.fs7.finalproject.eshop.model;

public enum Gender {
  MALE("M"), FEMALE("F");

  private final String shortName;

  Gender(String shortName) {
    this.shortName = shortName;
  }

  public String getShortName() {
    return this.shortName;
  }

  public static Gender fromShortName(String shortName) {
    switch (shortName) {
      case "M":
        return Gender.MALE;
      case "F":
        return Gender.FEMALE;
      default:
        throw new IllegalArgumentException("ShortName [" + shortName + "] not supported");
    }
  }
}
