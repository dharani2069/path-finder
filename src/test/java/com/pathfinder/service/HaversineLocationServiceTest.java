package com.pathfinder.service;

import com.pathfinder.models.Location;
import com.pathfinder.service.impl.HaversineLocationService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HaversineLocationServiceTest {

    private HaversineLocationService haversineLocationService;
    @Before
    public void setUp(){
        haversineLocationService = new HaversineLocationService();
    }

    /**
     * happy test case to verify calculateDistance of HaversineLocationService.
     */
    @Test
    public void calculateDistance(){
        Location location1 = new Location(13.631785571929992, 79.42875488094542);
        Location location2 = new Location(13.630075601887834, 79.41120250687892);
        double distance = haversineLocationService.calculateDistance(location1,location2);
        assertEquals(1.91, distance, 0.01);
    }

    /**
     * test case for illegeal arguements of calculateDistance in HaversineLocationService.
     */
    @Test(expected = IllegalArgumentException.class)
    public void calculateDistanceFOrInvalidInput(){
        Location location1 = new Location(13.631785571929992, 79.42875488094542);
        Location location2 = null;
        haversineLocationService.calculateDistance(location1, location2);
    }

}
