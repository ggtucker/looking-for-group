package com.alengeo.lfg.models;

import com.alengeo.lfg.models.Location;
import com.alengeo.lfg.models.SimpleUser;

import java.util.ArrayList;
import java.util.List;

/**
 * List entry for a locked in upcoming event for the user.
 */
public class LockedEvent {
    private String _id;
    private String title;
    private String description;
    private Location location;
    private String time;
    private String category;
    private int minPeople;
    private int maxPeople;
    private int minAge;
    private int maxAge;
    private int numPeople;
    private String organizer;
    private List<SimpleUser> attendees;

    public LockedEvent() {
        this._id = "";
        this.title = "";
        this.description = "";
        this.location = new Location();
        this.time = "";
        this.category = "";
        this.minPeople = 0;
        this.maxPeople = 0;
        this.minAge = 0;
        this.maxAge = 0;
        this.numPeople = 0;
        this.organizer = "";
        this.attendees = new ArrayList<>();
    }

    public LockedEvent(String title, String description, int numPeople) {
        this();
        this.title = title;
        this.description = description;
        this.numPeople = numPeople;
    }

    public LockedEvent(String _id, String title, String description, Location location, String time,
                       String category, int minPeople, int maxPeople, int minAge, int maxAge,
                       int numPeople, String organizer, List<SimpleUser> attendees) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.time = time;
        this.category = category;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.numPeople = numPeople;
        this.organizer = organizer;
        this.attendees = attendees;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(int minPeople) {
        this.minPeople = minPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) { this.numPeople = numPeople; }

    public List<SimpleUser> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<SimpleUser> attendees) {
        this.attendees = attendees;
    }
}
