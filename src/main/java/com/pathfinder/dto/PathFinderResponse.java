package com.pathfinder.dto;

import com.pathfinder.models.Location;

import java.util.List;

public class PathFinderResponse {
    private Double optimalTime;
    private List<Location> optimalPath;

    public PathFinderResponse(Double optimalTime, List<Location> optimalPath) {
        this.optimalTime = optimalTime;
        this.optimalPath = optimalPath;
    }

    public Double getOptimalTime() {
        return optimalTime;
    }

    public List<Location> getOptimalPath() {
        return optimalPath;
    }

    public void setOptimalTime(Double optimalTime) {
        this.optimalTime = optimalTime;
    }

    public void setOptimalPath(List<Location> optimalPath) {
        this.optimalPath = optimalPath;
    }
}
