package com.vojtoshik.problems;

import com.vojtoshik.problems.models.BusRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.ApplicationArguments;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class AppBeansTest {

    @Mock
    private ApplicationArguments applicationArguments;

    @InjectMocks
    private AppBeans appBeans;

    @Test(expected = RuntimeException.class)
    public void testCreateBusRouteDataProviderThrowsExceptionIfNoDataFilenameProvided() throws FileNotFoundException {
        when(applicationArguments.getNonOptionArgs()).thenReturn(Collections.emptyList());
        appBeans.createBusRouteDataProvider(applicationArguments);
    }

    @Test
    public void testCreateBusRouteDataProviderWorksAsExpected() throws FileNotFoundException {
        when(applicationArguments.getNonOptionArgs()).thenReturn(Arrays.asList(
                AppBeansTest.class.getResource("/input-files/correct-input.txt").getPath()
        ));

        Iterator<BusRoute> iterator = appBeans.createBusRouteDataProvider(applicationArguments);
        assertNotNull(iterator);
    }
}