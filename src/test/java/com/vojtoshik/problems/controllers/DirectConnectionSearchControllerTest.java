package com.vojtoshik.problems.controllers;

import com.vojtoshik.problems.models.SearchResultResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public class DirectConnectionSearchControllerTest {

    private DirectConnectionSearchController systemUnderTest = new DirectConnectionSearchController();

    @Test
    public void testFindConnectionWorksAsExpected() {
        int departureLocationId = 1987;
        int arrivalLocationId = 1988;

        SearchResultResponse response = systemUnderTest.findConnection(departureLocationId, arrivalLocationId);
        assertFalse(response.hasDirectConnection());
        assertEquals(departureLocationId, response.getDepartureLocationId());
        assertEquals(arrivalLocationId, response.getArrivalLocationId());
    }
}