package com.alengeo.lfg.models;

public class UserRatings {
    private int overall;
    private int humor;
    private int polite;
    private int punctual;
    private int sociable;
    private int numRatings;

    public UserRatings() {
        this.overall = 0;
        this.humor = 0;
        this.polite = 0;
        this.punctual = 0;
        this.sociable = 0;
        this.numRatings = 0;
    }

    public UserRatings(int overall, int humor, int polite, int punctual, int sociable, int numRatings) {
        this.overall = overall;
        this.humor = humor;
        this.polite = polite;
        this.punctual = punctual;
        this.sociable = sociable;
        this.numRatings = numRatings;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getHumor() {
        return humor;
    }

    public void setHumor(int humor) {
        this.humor = humor;
    }

    public int getPolite() {
        return polite;
    }

    public void setPolite(int polite) {
        this.polite = polite;
    }

    public int getPunctual() {
        return punctual;
    }

    public void setPunctual(int punctual) {
        this.punctual = punctual;
    }

    public int getSociable() {
        return sociable;
    }

    public void setSociable(int sociable) {
        this.sociable = sociable;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }
}
