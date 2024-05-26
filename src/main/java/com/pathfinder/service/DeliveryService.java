package com.pathfinder.service;

import com.pathfinder.dto.PathFinderResponse;
import com.pathfinder.helper.PathsGenerator;
import com.pathfinder.models.Location;
import com.pathfinder.models.Order;
import com.pathfinder.models.Restaurant;
import com.pathfinder.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pathfinder.utils.Constants.RESTAURANTS_CONSUMERS_EMPTY_ERROR_MESSAGE;

public class DeliveryService {

    private final double AVERAGE_SPEED_KMH = Constants.AVERAGE_SPEED;

    private final LocationService locationService;

    public DeliveryService(LocationService locationService){
        this.locationService = locationService;
    }

    public double calculateTravelTime(double distance) {
        return distance / AVERAGE_SPEED_KMH;
    }

    /**
     * @param startLocation starting/current location .
     * @param path consists of locations delivery agent take from first element to last element in order.
     * @param restaurantMap key is location string of restaurant and value is its respective restaurant.
     * @return Returns total time in hrs taken by following {@code path} from {@code startLocation}.
     */
    public double calculatePathTime(Location startLocation, List<Location> path, Map<String, Restaurant> restaurantMap) {
        double time = 0;
        Location currentLocation = startLocation;

        for (Location location : path) {
            double distanceToLocation = locationService.calculateDistance(currentLocation, location);
            time += calculateTravelTime(distanceToLocation);
            if(restaurantMap.containsKey(location.toString())){
                // time is max of (restaurant average preparation time, total time to reach the restaurant).
                time = Math.max(time, restaurantMap.get(location.toString()).getAveragePreparationTime() /60.0);
            }
            currentLocation = location;
        }
        return time; // in hours
    }

    /**
     * @param orderList     batch of orders need to be delivered.
     * @param startLocation starting/current location .
     * @return {@link PathFinderResponse}  minimum time & optimal path needs to be taken to complete the batch of orders in minimum time.
     * @throws IllegalArgumentException for an illegal param {@code orderList} if any order contains empty restaurant or consumer details.
     */
    public PathFinderResponse findOptimalPath(List<Order> orderList, Location startLocation){
        double minTime = Double.MAX_VALUE;
        List<Location> optimalPath = null;

        List<Location> restaurants = new ArrayList<>();
        List<Location> consumers = new ArrayList<>();
        Map<String, Restaurant> restaurantMap = new HashMap<>();

        try {
            orderList.forEach(order -> {
                restaurants.add(order.getPickUp().getLocation());
                consumers.add(order.getDrop().getLocation());
                restaurantMap.put(order.getPickUp().getLocation().toString(), order.getPickUp());
            });
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(RESTAURANTS_CONSUMERS_EMPTY_ERROR_MESSAGE);
        }

        List<List<Location>> validPaths = PathsGenerator.generateValidPaths(restaurants, consumers);
        for(List<Location> path : validPaths){
            double time = calculatePathTime(startLocation, path, restaurantMap);
            if(minTime>time){
                minTime = time;
                optimalPath = path;
            }
        }
        return new PathFinderResponse(minTime, optimalPath);
    }

}
