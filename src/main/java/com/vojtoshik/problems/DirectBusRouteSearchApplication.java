package com.vojtoshik.problems;

import com.vojtoshik.problems.services.DirectConnectionLookupService;
import com.vojtoshik.problems.services.InMemoryDirectConnectionLookupService;
import com.vojtoshik.problems.services.RoutesDataReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileNotFoundException;

@SpringBootApplication
@ComponentScan(basePackages = "com.vojtoshik.problems")
public class DirectBusRouteSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectBusRouteSearchApplication.class, args);
    }

    @Bean
    public DirectConnectionLookupService createLookupService() throws FileNotFoundException {
        return new InMemoryDirectConnectionLookupService(
                new RoutesDataReader("./test-input.txt")
        );
    }
}
