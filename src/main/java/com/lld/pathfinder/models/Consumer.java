package main.java.com.lld.pathfinder.models;


public class Consumer {
    String name;
    Location location;

    public Consumer(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
