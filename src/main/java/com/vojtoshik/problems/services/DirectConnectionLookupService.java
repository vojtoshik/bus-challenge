package com.vojtoshik.problems.services;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public interface DirectConnectionLookupService {
    boolean lookup(int departureLocationId, int arrivalLocationId);
}
