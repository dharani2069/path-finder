package com.pathfinder;

import com.pathfinder.dto.PathFinderResponse;
import com.pathfinder.models.Consumer;
import com.pathfinder.models.Location;
import com.pathfinder.models.Order;
import com.pathfinder.models.Restaurant;
import com.pathfinder.service.DeliveryService;
import com.pathfinder.service.LocationService;
import com.pathfinder.service.impl.HaversineLocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//driver class
public class PathFinderApplication {
    public static void main(String[] args) {

        LocationService locationService = new HaversineLocationService();
        DeliveryService deliveryService = new DeliveryService(locationService);
        List<Order> orderList = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        for(int i = 0;i<2;i++) {

            System.out.println("provide consumer" + i + " details.");
            float latitude = in.nextFloat();
            float longitude = in.nextFloat();
            Consumer consumer = new Consumer("consumer"+i, new Location(latitude, longitude));

            System.out.println("provide Restaurant" + i + " details.");
            float latitude11 = in.nextFloat();
            float longitude11 = in.nextFloat();
            double averageTime = in.nextDouble();
            Restaurant restaurant = new Restaurant(averageTime, "restaurant"+i, new Location(latitude11, longitude11));
            orderList.add(new Order(i, consumer, restaurant));
        }
        System.out.println("provide users current location");
        float lat = in.nextFloat();
        float lon = in.nextFloat();
        Location currentLocation = new Location(lat, lon);

        PathFinderResponse pathFinderResponse = deliveryService.findOptimalPath(orderList, currentLocation);
        pathFinderResponse.getOptimalPath().forEach(location -> {
            System.out.print("(" + location.toString() + ") -> ");
        });
        System.out.print("end\n");
    }
}