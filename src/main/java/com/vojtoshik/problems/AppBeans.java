package com.vojtoshik.problems;

import com.vojtoshik.problems.models.BusRoute;
import com.vojtoshik.problems.services.DirectConnectionLookupService;
import com.vojtoshik.problems.services.InMemoryDirectConnectionLookupService;
import com.vojtoshik.problems.services.RoutesDataReader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
@Configuration
public class AppBeans {

    @Bean
    public Iterator<BusRoute> createBusRouteDataProvider(ApplicationArguments applicationArguments)
            throws FileNotFoundException {

        if (applicationArguments.getNonOptionArgs().isEmpty()) {
            throw new RuntimeException("Data file name not provided");
        }

        return new RoutesDataReader(applicationArguments.getNonOptionArgs().get(0));
    }
}
