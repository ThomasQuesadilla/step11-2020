package com.google.sps.data;

import static java.lang.Math.toIntExact;

import com.google.appengine.api.datastore.Entity;
import com.google.sps.util.ParameterConstants;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Mentee extends UserAccount {

  private Topic goal;
  private MeetingFrequency desiredMeetingFrequency;
  private Set<Long> dislikedMentorKeys;
  private MentorType desiredMentorType;

  private Mentee(Builder builder) {
    super(builder);
    this.goal = builder.goal;
    this.desiredMeetingFrequency = builder.desiredMeetingFrequency;
    this.dislikedMentorKeys = builder.dislikedMentorKeys;
    this.desiredMentorType = builder.desiredMentorType;
    this.validate();
  }

  public Mentee(Entity entity) {
    super(entity);
    this.goal =
        Topic.values()[toIntExact((long) entity.getProperty(ParameterConstants.MENTEE_GOAL))];
    this.desiredMeetingFrequency =
        MeetingFrequency.values()[
            toIntExact(
                (long) entity.getProperty(ParameterConstants.MENTEE_DESIRED_MEETING_FREQUENCY))];
    this.dislikedMentorKeys =
        getDislikedSetFromProperty(
            (Collection) entity.getProperty(ParameterConstants.MENTEE_DISLIKED_MENTOR_KEYS));
    this.desiredMentorType =
        MentorType.values()[toIntExact((long) entity.getProperty(ParameterConstants.MENTOR_TYPE))];
    this.validate();
  }

  @Override
  protected void validate() {
    super.validate();
    if (this.dislikedMentorKeys == null) {
      this.dislikedMentorKeys = new HashSet<>();
    }
  }

  public Entity convertToEntity() {
    Entity entity = super.convertToEntity();
    entity.setProperty(ParameterConstants.MENTEE_GOAL, goal.ordinal());
    entity.setProperty(
        ParameterConstants.MENTEE_DESIRED_MEETING_FREQUENCY, desiredMeetingFrequency.ordinal());
    entity.setProperty(ParameterConstants.MENTEE_DISLIKED_MENTOR_KEYS, this.dislikedMentorKeys);
    entity.setProperty(ParameterConstants.MENTOR_TYPE, desiredMentorType.ordinal());
    return entity;
  }

  private static Set<Long> getDislikedSetFromProperty(Collection<Object> dislikedMentorKeyList) {
    return dislikedMentorKeyList == null
        ? new HashSet<Long>()
        : (Set<Long>)
            dislikedMentorKeyList.stream()
                .map(key -> new Long((long) key))
                .collect(Collectors.toSet());
  }

  public boolean dislikeMentor(Mentor mentor) {
    return dislikedMentorKeys.add(mentor.getDatastoreKey());
  }

  public Topic getGoal() {
    return goal;
  }

  public MentorType getDesiredMentorType() {
    return desiredMentorType;
  }

  public MeetingFrequency getDesiredMeetingFrequency() {
    return desiredMeetingFrequency;
  }

  public Set<Long> getDislikedMentorKeys() {
    return this.dislikedMentorKeys;
  }

  public static class Builder extends UserAccount.Builder<Builder> {
    private Topic goal;
    private MeetingFrequency desiredMeetingFrequency;
    private Set<Long> dislikedMentorKeys;
    private MentorType desiredMentorType;

    public Builder() {}

    public Builder goal(Topic goal) {
      this.goal = goal;
      return this;
    }

    public Builder desiredMeetingFrequency(MeetingFrequency desiredMeetingFrequency) {
      this.desiredMeetingFrequency = desiredMeetingFrequency;
      return this;
    }

    public Builder dislikedMentorKeys(Set<Long> dislikedMentorKeys) {
      this.dislikedMentorKeys = dislikedMentorKeys;
      return this;
    }

    public Builder desiredMentorType(MentorType desiredMentorType) {
      this.desiredMentorType = desiredMentorType;
      return this;
    }

    public Mentee build() {
      return new Mentee(this);
    }
  }
}
