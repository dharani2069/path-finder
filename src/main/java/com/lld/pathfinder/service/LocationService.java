package main.java.com.lld.pathfinder.service;

import main.java.com.lld.pathfinder.models.Location;
import main.java.com.lld.pathfinder.utils.Constants;

public interface LocationService {

    public double calculateDistance(Location loc1, Location loc2);
}
