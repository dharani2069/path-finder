package main.java.com.lld.pathfinder.service.impl;

import main.java.com.lld.pathfinder.models.Location;
import main.java.com.lld.pathfinder.service.LocationService;
import main.java.com.lld.pathfinder.utils.Constants;

public class HaversineLocationService implements LocationService {
    @Override
    public double calculateDistance(Location loc1, Location loc2) {
        double latDistance = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double lonDistance = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude())) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Constants.EARTH_RADIUS_KM * c; // in km
    }
}
