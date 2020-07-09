package com.google.sps.data;

/**
 * This class represents the possible types for a user to be.
 */
public enum UserType {
  MENTOR("Mentor"),
  MENTEE("Mentee");

  private String title;

  private UserType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
