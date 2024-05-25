package main.java.com.lld.pathfinder.service;

import main.java.com.lld.pathfinder.helper.PathsGenerator;
import main.java.com.lld.pathfinder.models.Location;
import main.java.com.lld.pathfinder.models.Order;
import main.java.com.lld.pathfinder.models.Restaurant;
import main.java.com.lld.pathfinder.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryService {

    private final double AVERAGE_SPEED_KMH = Constants.AVERAGE_SPEED;

    private final LocationService locationService;

    public DeliveryService(LocationService locationService){
        this.locationService = locationService;
    }

    public double calculateTravelTime(double distance) {
        return distance / AVERAGE_SPEED_KMH;
    }



    public double calculateRouteTime(Location startLocation, List<Location> path, Map<String, Restaurant> restaurantMap) {
        double time = 0;
        Location currentLocation = startLocation;

        for (Location location : path) {
            double distanceToLocation = locationService.calculateDistance(currentLocation, location);
            time += calculateTravelTime(distanceToLocation);
            if(restaurantMap.containsKey(location.toString())){
               time = Math.max(time, restaurantMap.get(location.toString()).getAverageTime() /60.0);
            }
            currentLocation = location;
        }
        return time; // in hours
    }


    public List<Location> findOptimalRoute(List<Order> orderList, Location startLocation){
        List<Location> restaurants = new ArrayList<>();
        List<Location> consumers = new ArrayList<>();
        Map<String, Restaurant> restaurantMap = new HashMap<>();
        orderList.forEach(order -> {
            restaurants.add(order.getPickUp().getLocation());
            consumers.add(order.getDrop().getLocation());
            restaurantMap.put(order.getPickUp().getLocation().toString(), order.getPickUp());
        });

        double minTime = Double.MAX_VALUE;
        List<Location> optimalPath = null;
        List<List<Location>> validPaths = PathsGenerator.generateValidPermutations(restaurants, consumers);
        for(List<Location> path : validPaths){
            double time = calculateRouteTime(startLocation, path, restaurantMap);
            if(minTime>time){
                minTime = time;
                optimalPath = path;
            }
        }
        return optimalPath;
    }



}
