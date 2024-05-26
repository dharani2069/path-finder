package com.pathfinder.service.impl;

import com.pathfinder.models.Location;
import com.pathfinder.service.LocationService;
import com.pathfinder.utils.Constants;

import static com.pathfinder.utils.Constants.LOCATION_EMPTY_ERROR_MESSAGE;

/**
 *  implementation of the {@link LocationService} interface.
 *  leverages Haversine formula to calculate distance between locations.
 */
public class HaversineLocationService implements LocationService {

    @Override
    public double calculateDistance(Location loc1, Location loc2) {
        if (loc1 == null || loc2 == null) {
            throw new IllegalArgumentException(LOCATION_EMPTY_ERROR_MESSAGE);
        }
        double latDistance = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double lonDistance = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude())) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Constants.EARTH_RADIUS_KM * c; // in km
    }
}
