package com.alengeo.lfg.models;

public class SimpleUser {
    private String _id;
    private String description;
    private int age;
    private Location location;
    private UserRatings rating;

    public SimpleUser() {
        this._id = "";
        this.description = "";
        this.age = 0;
        this.location = new Location();
        this.rating = new UserRatings();
    }

    public SimpleUser(String _id, String description, int age, Location location, UserRatings rating) {
        this._id = _id;
        this.description = description;
        this.age = age;
        this.location = location;
        this.rating = rating;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UserRatings getRating() {
        return rating;
    }

    public void setRating(UserRatings rating) {
        this.rating = rating;
    }
}
