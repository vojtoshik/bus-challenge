package com.vojtoshik.problems.services;

import com.vojtoshik.problems.models.BusRoute;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public class RoutesDataReaderTest {

    @Test(expected = FileNotFoundException.class)
    public void testReaderThrowsExceptionWhenFileDoesNotExist() throws FileNotFoundException {
        new RoutesDataReader("fakefile.txt");
    }

    @Test(expected = BadDataFileFormat.class)
    public void testReaderThrowsExceptionWhenNumberOfRoutesNotSpecified() throws FileNotFoundException {
        new RoutesDataReader(getTestInputFilePath("empty.txt"));
    }

    @Test(expected = BadDataFileFormat.class)
    public void testReaderThrowsException() throws FileNotFoundException {
        Iterator<BusRoute> iterator = new RoutesDataReader(getTestInputFilePath("wrong-number-of-routes.txt"));

        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Test
    public void testReaderWorksAsExpected() throws FileNotFoundException {
        Iterator<BusRoute> iterator = new RoutesDataReader(getTestInputFilePath("correct-input.txt"));

        int counter = 1;

        while (iterator.hasNext()) {
            BusRoute route = iterator.next();
            assertEquals(counter, route.getBusRouteId());

            List<Integer> stopsList = route.getRouteStopsList();
            assertNotNull(stopsList);
            assertEquals(counter, stopsList.size());

            IntStream.range(0, counter)
                    .forEach(i -> assertEquals(i + 1, (int)stopsList.get(i)));
            counter++;
        }
    }

    private String getTestInputFilePath(String filename) {
        return RoutesDataReaderTest.class.getResource("/input-files/" + filename).getPath();
    }
}