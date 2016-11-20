package com.vojtoshik.problems.controllers;

import com.vojtoshik.problems.models.SearchResultResponse;
import com.vojtoshik.problems.services.DirectConnectionLookupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class DirectConnectionSearchControllerTest {

    @Mock
    private DirectConnectionLookupService lookupServiceMock;

    @InjectMocks
    private DirectConnectionSearchController systemUnderTest;

    @Test
    public void testFindConnection() {
        testFindConnectionWorksAsExpected(19, 87, false);
        testFindConnectionWorksAsExpected(19, 87, true);
    }

    private void testFindConnectionWorksAsExpected(int inputDepartureLocationId,
                                                   int inputArrivalLocationId,
                                                   boolean serviceReply) {

        when(lookupServiceMock.lookup(inputDepartureLocationId, inputArrivalLocationId))
                .thenReturn(serviceReply);

        SearchResultResponse response = systemUnderTest
                .findConnection(inputDepartureLocationId, inputArrivalLocationId);

        assertEquals(serviceReply, response.hasDirectConnection());
        assertEquals(inputDepartureLocationId, response.getDepartureLocationId());
        assertEquals(inputArrivalLocationId, response.getArrivalLocationId());
    }
}