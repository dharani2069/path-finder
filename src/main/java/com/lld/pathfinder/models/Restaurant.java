package main.java.com.lld.pathfinder.models;


public class Restaurant {
    String name;
    Location location;
    double averageTime;

    public Restaurant(double averageTime, String name, Location location) {
        this.averageTime = averageTime;
        this.name = name;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public double getAverageTime() {
        return averageTime;
    }
}
