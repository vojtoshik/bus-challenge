package com.vojtoshik.problems.services;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
@Named
public class InMemoryDirectConnectionLookupService implements DirectConnectionLookupService {

    private Map<Integer, List<Integer>> stopsToRoutesList = new HashMap<>();

    public InMemoryDirectConnectionLookupService() {
        stopsToRoutesList.put(1, Arrays.asList(100, 110, 190));
        stopsToRoutesList.put(2, Arrays.asList(120, 130, 140, 150, 160, 170, 180));
        stopsToRoutesList.put(3, Arrays.asList(120, 140));
        stopsToRoutesList.put(4, Arrays.asList(140, 150));
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
