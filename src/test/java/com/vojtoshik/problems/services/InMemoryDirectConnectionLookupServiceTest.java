package com.vojtoshik.problems.services;

import com.vojtoshik.problems.models.BusRoute;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public class InMemoryDirectConnectionLookupServiceTest {

    private InMemoryDirectConnectionLookupService systemUnderTest;

    @Before
    public void setup() {
        List<BusRoute> routes = Arrays.asList(
                new BusRoute(100, Arrays.asList(9, 8, 7, 6, 5, 3)),
                new BusRoute(111, Arrays.asList(9, 2)),
                new BusRoute(112, Arrays.asList(4, 2, 1)),
                new BusRoute(200, Arrays.asList(8, 9))
        );

        systemUnderTest = new InMemoryDirectConnectionLookupService(routes.iterator());
    }

    @Test
    public void testLookupWorksAsExpected() {
        assertTrue(systemUnderTest.lookup(3, 9));
        assertTrue(systemUnderTest.lookup(1, 4));
        assertTrue(systemUnderTest.lookup(9, 8));
        assertFalse(systemUnderTest.lookup(2, 3));
        assertFalse(systemUnderTest.lookup(4, 9));
    }
}