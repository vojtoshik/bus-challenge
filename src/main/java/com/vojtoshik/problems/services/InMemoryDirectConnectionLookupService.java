package com.vojtoshik.problems.services;

import com.vojtoshik.problems.models.BusRoute;

import javax.inject.Named;
import java.util.*;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
@Named
public class InMemoryDirectConnectionLookupService implements DirectConnectionLookupService {

    private Map<Integer, List<Integer>> stopsToRoutesList = new HashMap<>();

    public InMemoryDirectConnectionLookupService(List<BusRoute> busRoutesList) {

        busRoutesList.stream().forEach((busRoute) ->
            busRoute.getRouteStopsList()
                    .stream()
                    .forEach((stopId) -> addRouteToBusStop(stopId, busRoute.getBusRouteId()))
        );

        stopsToRoutesList.forEach((stopId, routesList) -> Collections.sort(routesList));
    }

    public boolean lookup(int departureBusStopId, int arrivalBusStopId) {
        if (!stopsToRoutesList.containsKey(departureBusStopId) || !stopsToRoutesList.containsKey(arrivalBusStopId)) {
            return false;
        }

        List<Integer> routesListToIterate = getSmallerRoutesList(departureBusStopId, arrivalBusStopId);
        List<Integer> routesListForBinarySearch = getBiggerRoutesList(departureBusStopId, arrivalBusStopId);

        Optional<Integer> result = routesListToIterate.stream()
                .filter((route) -> Collections.binarySearch(routesListForBinarySearch, route) >= 0)
                .findFirst();

        return result.isPresent();
    }

    private void addRouteToBusStop(Integer stopId, int busRouteId) {
        if (!stopsToRoutesList.containsKey(stopId)) {
            stopsToRoutesList.put(stopId, new ArrayList<>());
        }

        stopsToRoutesList.get(stopId).add(busRouteId);
    }

    private List<Integer> getSmallerRoutesList(int busStopA, int busStopB) {
        return getRoute(busStopA, busStopB, (a, b) -> Integer.valueOf(b.size()).compareTo(a.size()));
    }

    private List<Integer> getBiggerRoutesList(int busStopA, int busStopB) {
        return getRoute(busStopA, busStopB, (a, b) -> Integer.valueOf(a.size()).compareTo(b.size()));
    }

    private List<Integer> getRoute(int busStopA, int busStopB, Comparator<List<Integer>> comparator) {
        List<Integer> routesA = stopsToRoutesList.get(busStopA);
        List<Integer> routesB = stopsToRoutesList.get(busStopB);

        return comparator.compare(routesA, routesB) > 0
                ? routesA
                : routesB;
    }
}
