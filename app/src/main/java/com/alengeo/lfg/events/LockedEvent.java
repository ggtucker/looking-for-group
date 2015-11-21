package com.alengeo.lfg.events;

import java.util.ArrayList;
import java.util.List;

/**
 * List entry for a locked in upcoming event for the user.
 */
public class LockedEvent {
    private String _id;
    private String title;
    private String description;
    private String location;
    private String time;
    private String category;
    private int minPeople;
    private int maxPeople;
    private int minAge;
    private int maxAge;
    private String organizer;
    private List<String> attendees;

    public LockedEvent(String title, String description, int numPeople) {
        this.title = title;
        this.description = description;
        this.attendees = new ArrayList<>();
        for(int i = 0; i < numPeople; ++i) {
            attendees.add("" + i);
        }
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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
        return attendees.size();
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }
}
