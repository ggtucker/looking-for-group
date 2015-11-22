package com.alengeo.lfg.models;

/**
 * List entry for an event not yet locked in.
 */
public class TentativeEvent {
    private String _id;
    private String title;
    private String description;
    private String time;
    private String category;
    private int minPeople;
    private int maxPeople;
    private int minAge;
    private int maxAge;
    private int numPeople;

    public TentativeEvent() {
        this._id = "";
        this.title = "";
        this.description = "";
        this.time = "";
        this.category = "";
        this.minPeople = 0;
        this.maxPeople = 0;
        this.minAge = 0;
        this.maxAge = 0;
        this.numPeople = 0;
    }

    public TentativeEvent(String title, String description, int minPeople, int maxPeople,
                          int numPeople) {
        this();
        this.title = title;
        this.description = description;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.numPeople = numPeople;
    }

    public TentativeEvent(String _id, String title, String description, String time,
                          String category, int minPeople, int maxPeople, int minAge, int maxAge,
                          int numPeople) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.category = category;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.numPeople = numPeople;
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

    public String getPeopleRange() {
        return minPeople + "-" + maxPeople;
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

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }
}
