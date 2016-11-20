package com.vojtoshik.problems.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public class SearchResultResponse {

    @JsonProperty("dep_sid")
    private int departureLocationId;

    @JsonProperty("arr_sid")
    private int arrivalLocationId;

    @JsonProperty("direct_bus_route")
    private boolean hasDirectConnection;

    public SearchResultResponse(int departureLocationId, int arrivalLocationId, boolean hasDirectConnection) {
        this.departureLocationId = departureLocationId;
        this.arrivalLocationId = arrivalLocationId;
        this.hasDirectConnection = hasDirectConnection;
    }

    public int getDepartureLocationId() {
        return departureLocationId;
    }

    public int getArrivalLocationId() {
        return arrivalLocationId;
    }

    public boolean hasDirectConnection() {
        return hasDirectConnection;
    }
}
