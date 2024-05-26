package com.pathfinder.models;


/**
 * A class representing restaurants name, location and averagePreparationTime in minutes.
 */
public class Restaurant {
    String name;
    Location location;
    double averagePreparationTime; // in minutes

    public Restaurant(double averageTime, String name, Location location) {
        this.averagePreparationTime = averageTime;
        this.name = name;
        this.location = location;
    }

    public Restaurant() {
    }

    public Location getLocation() {
        return location;
    }

    public double getAveragePreparationTime() {
        return averagePreparationTime;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
