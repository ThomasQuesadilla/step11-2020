package com.google.sps.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;

public class TimeZoneInfo {

  private static final double MILLISECONDS_PER_HOURS = 3600000.0;

  String name;
  String id;
  double offset;

  public TimeZoneInfo(TimeZone timeZone) {
    this.name = timeZone.getDisplayName();
    this.id = timeZone.getID();
    this.offset = timeZone.getRawOffset() / MILLISECONDS_PER_HOURS;
  }

  public String getName() {
    return this.name;
  }

  public String getID() {
    return this.id;
  }

  public double getOffset() {
    return this.offset;
  }

  public static Collection<TimeZoneInfo> getListOfNamesToDisplay(Collection<TimeZone> timeZones) {
    Collection<TimeZoneInfo> infoList = new ArrayList<>();
    for (TimeZone timeZone : timeZones) {
      infoList.add(new TimeZoneInfo(timeZone));
    }
    return infoList;
  }
}
