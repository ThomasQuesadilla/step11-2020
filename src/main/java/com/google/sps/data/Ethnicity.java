package com.google.sps.data;

/**
 * This class represents a user's ethnicity.
 */
public enum Ethnicity {
  UNSPECIFIED(""),
  ASIAN("Asian"),
  BLACK_AFRICAN("Black African"),
  CAUCASIAN("Caucasian"),
  INDIAN("Indian"),
  HISPANIC("Hispanic"),
  NATIVE_AMERICAN("Native American"),
  PACIFIC_ISLANDER("Pacific Islander"),
  OTHER("Other");

  private String title;

  private Ethnicity(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
