package main.java.com.lld.pathfinder;

import main.java.com.lld.pathfinder.models.Consumer;
import main.java.com.lld.pathfinder.models.Location;
import main.java.com.lld.pathfinder.models.Order;
import main.java.com.lld.pathfinder.models.Restaurant;
import main.java.com.lld.pathfinder.service.DeliveryService;
import main.java.com.lld.pathfinder.service.LocationService;
import main.java.com.lld.pathfinder.service.impl.HaversineLocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//driver class
/*·         Modularity - loosely coupled

·         Encapsulation - hide state

·         Readability - readable

·         Extensibility - open for extension i.e try to avoid touching existing code even if new
assumptions are made or existing constraints are modified.
* */
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

        List<Location> path = deliveryService.findOptimalRoute(orderList, currentLocation);
        path.forEach(location -> {
            System.out.print("(" + location.toString() + ") -> ");
        });
        System.out.print("end");
    }
}