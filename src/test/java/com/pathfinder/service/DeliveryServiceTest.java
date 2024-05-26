package com.pathfinder.service;

import com.pathfinder.dto.PathFinderResponse;
import com.pathfinder.models.Consumer;
import com.pathfinder.models.Order;
import com.pathfinder.models.Restaurant;
import com.pathfinder.service.impl.HaversineLocationService;
import com.pathfinder.models.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DeliveryServiceTest {

    private DeliveryService deliveryService;
    private Location startLocation;
    private List<Order> orderList;

    @Before
    public void setUp() {
        deliveryService = new DeliveryService(new HaversineLocationService());
        orderList = new ArrayList<>();
        startLocation = new Location(13.631785571929992, 79.42875488094542);

        int ordersSize = 2;
        String[] names = new String[]{"restaurant1","consumer1","restaurant2","consumer2"};
        double[] latitudesList = new double[]{13.630075601887834, 13.628782689494075, 13.635747650074023, 13.628574154574528};
        double[] longitudesList = new double[]{79.41120250687892, 79.41939933682194, 79.42201717256293, 79.4065247348172};
        double[] averagePreparationTime = new double[]{10,20};


        for(int i = 0; i< ordersSize; i++){
            Restaurant r = new Restaurant(averagePreparationTime[i],
                    names[i* ordersSize],
                    new Location(latitudesList[i* ordersSize],longitudesList[i* ordersSize]));
            Consumer c = new Consumer(names[i* ordersSize + 1],
                    new Location(latitudesList[i* ordersSize + 1],longitudesList[i* ordersSize + 1]));
            orderList.add(new Order(i,c,r));
        }
    }

    /**
     * happy test case to verify calculateTravelTime of deliveryService.
     */
    @Test
    public void calculateTravelTime() {
        double distance = 20.0; // in km
        double travelTime = deliveryService.calculateTravelTime(distance);
        assertEquals(1.0, travelTime, 0.01);
    }

    /**
     * test case for illegeal arguements of findOptimalPath in deliveryService.
     */
    @Test(expected = IllegalArgumentException.class)
    public void findOptimalPathForInvalidInput(){
        orderList.get(0).setPickUp(null);
        deliveryService.findOptimalPath(orderList, startLocation);
    }
    /**
     * happy test case to verify findOptimalPath of deliveryService.
     */
    @Test
    public void findOptimalPath() {
        double optimalTime = 0.42; // in hr
        Location location1 = new Location(13.630075601887834,79.41120250687892);
        Location location2 = new Location(13.628782689494075,79.41939933682194);
        Location location3 = new Location(13.635747650074023,79.42201717256293);
        Location location4 = new Location(13.628574154574528,79.4065247348172);
        List<Location> optimalPath = new ArrayList<>(){};
        optimalPath.add(location1);
        optimalPath.add(location2);
        optimalPath.add(location3);
        optimalPath.add(location4);
        PathFinderResponse response =
                deliveryService.findOptimalPath(orderList, startLocation);

        assertEquals(optimalTime, response.getOptimalTime(), 0.01);

        for(int i = 0;i<optimalPath.size();i++){
            String expected = optimalPath.get(i).toString();
            String actual = response.getOptimalPath().get(i).toString();
            assertEquals(expected, actual);
        }
    }

}
