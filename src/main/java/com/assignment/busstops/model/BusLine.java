package com.assignment.busstops.model;

import java.util.List;

public record BusLine(String lineName, List<BusStop> stops) {
    public record BusStop(String name) {}
    public Integer size() {
        return stops.size();
    }
}