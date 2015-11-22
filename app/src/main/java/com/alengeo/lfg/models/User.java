package com.alengeo.lfg.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String _id;
    private String facebookId;
    private String description;
    private Location location;
    private String birthday;
    private UserRatings rating;
    private List<LockedEvent> upcomingEvents;
    private List<TentativeEvent> organizedEvents;
    private List<TentativeEvent> joinedEvents;

    public User() {
        this._id = "";
        this.facebookId = "";
        this.description = "";
        this.location = new Location();
        this.birthday = "";
        this.rating = new UserRatings();
        this.upcomingEvents = new ArrayList<>();
        this.organizedEvents = new ArrayList<>();
        this.joinedEvents = new ArrayList<>();
    }

    public User(String _id, String facebookId, String description, Location location,
                String birthday, UserRatings rating, List<LockedEvent> upcomingEvents,
                List<TentativeEvent> organizedEvents, List<TentativeEvent> joinedEvents) {
        this._id = _id;
        this.facebookId = facebookId;
        this.description = description;
        this.location = location;
        this.birthday = birthday;
        this.rating = rating;
        this.upcomingEvents = upcomingEvents;
        this.organizedEvents = organizedEvents;
        this.joinedEvents = joinedEvents;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public UserRatings getRating() {
        return rating;
    }

    public void setRating(UserRatings rating) {
        this.rating = rating;
    }

    public List<LockedEvent> getUpcomingEvents() {
        return upcomingEvents;
    }

    public void setUpcomingEvents(List<LockedEvent> upcomingEvents) {
        this.upcomingEvents = upcomingEvents;
    }

    public List<TentativeEvent> getOrganizedEvents() {
        return organizedEvents;
    }

    public void setOrganizedEvents(List<TentativeEvent> organizedEvents) {
        this.organizedEvents = organizedEvents;
    }

    public List<TentativeEvent> getJoinedEvents() {
        return joinedEvents;
    }

    public void setJoinedEvents(List<TentativeEvent> joinedEvents) {
        this.joinedEvents = joinedEvents;
    }
}
