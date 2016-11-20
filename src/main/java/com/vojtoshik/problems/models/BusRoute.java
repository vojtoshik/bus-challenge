package com.vojtoshik.problems.models;

import java.util.List;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public class BusRoute {
    private int busRouteId;
    private List<Integer> routeStopsList;

    public BusRoute(int busRouteId, List<Integer> routeStopsList) {
        this.busRouteId = busRouteId;
        this.routeStopsList = routeStopsList;
    }

    public int getBusRouteId() {
        return busRouteId;
    }

    public List<Integer> getRouteStopsList() {
        return routeStopsList;
    }
}
