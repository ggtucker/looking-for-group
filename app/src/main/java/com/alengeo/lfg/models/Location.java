package com.alengeo.lfg.models;

public class Location {
    private String city;
    private String state;

    public Location() {
        this.city = "";
        this.state = "";
    }

    public Location(String cityAndState) {
        assert cityAndState != null;
        String[] split = cityAndState.trim().split(",");
        if(split.length == 2) {
            this.city = split[0].trim();
            this.state = split[1].trim();
        } else {
            this.city = split[0];
            this.state = split[0];
        }
    }

    public Location(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
