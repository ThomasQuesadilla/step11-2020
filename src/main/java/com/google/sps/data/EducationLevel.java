package com.google.sps.data;

/**
 * This class represents a user's education level.
 */
public enum EducationLevel {
  UNSPECIFIED(""),
  NONE("None"),
  HIGHSCHOOL("Highschool Diploma"),
  ASSOCIATES("Associate's Degree"),
  BACHELORS("Bachelor's Degree"),
  MASTERS("Master's Degree"),
  DOCTORATE("Doctoral Degree"),
  OTHER("Other");

  private String title;

  private EducationLevel(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
