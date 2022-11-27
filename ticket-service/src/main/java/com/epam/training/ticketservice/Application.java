package com.epam.training.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Entry point for this application.
 *
 * @author mezeimate
 */
@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
