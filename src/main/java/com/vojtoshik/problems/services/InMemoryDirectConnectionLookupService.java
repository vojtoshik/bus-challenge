package com.vojtoshik.problems.services;

import javax.inject.Named;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
@Named
public class InMemoryDirectConnectionLookupService implements DirectConnectionLookupService {

    public boolean lookup(int departureLocationId, int arrivalLocationId) {
        return false;
    }
}
