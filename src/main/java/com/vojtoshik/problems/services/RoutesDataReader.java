package com.vojtoshik.problems.services;

import com.vojtoshik.problems.models.BusRoute;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public class RoutesDataReader implements Iterator<BusRoute> {

    private int routesCount;

    private int currentLineNumber;

    private Scanner fileScanner;

    public RoutesDataReader(String dataFileName) throws FileNotFoundException {
        fileScanner = new Scanner(new File(dataFileName));

        if (!fileScanner.hasNextInt()) {
            throw new BadDataFileFormat("No number of routes provided in input file");
        }

        routesCount = fileScanner.nextInt();
        fileScanner.nextLine();
    }

    @Override
    public boolean hasNext() {
        return currentLineNumber < routesCount;
    }

    @Override
    public BusRoute next() {

        if (!hasNext()) {
            throw new NoSuchElementException("File has been completely read");
        }

        if (!fileScanner.hasNextLine()) {
            throw new BadDataFileFormat("Unexpectedly reached end of file");
        }

        Scanner lineScanner = new Scanner(fileScanner.nextLine());

        if (!lineScanner.hasNextInt()) {
            throw new BadDataFileFormat("Route id not found");
        }

        int routeId = lineScanner.nextInt();
        List<Integer> stopsList = new ArrayList<>();

        while (lineScanner.hasNextInt()) {
            stopsList.add(lineScanner.nextInt());
        }

        currentLineNumber++;
        return new BusRoute(routeId, stopsList);
    }
}
