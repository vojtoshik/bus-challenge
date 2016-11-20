package com.vojtoshik.problems.services;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public class InMemoryDirectConnectionLookupServiceTest {

    private InMemoryDirectConnectionLookupService systemUnderTest = new InMemoryDirectConnectionLookupService();

    @Test
    public void testLookupWorksAsExpected() {
        assertFalse(systemUnderTest.lookup(1, 2));
    }
}