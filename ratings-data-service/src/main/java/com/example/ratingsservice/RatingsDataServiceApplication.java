package com.example.ratingsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


@SpringBootApplication //automatically detect:RatingsResource (controller) RatingRepository (repository) Rating (entity)
@EnableEurekaClient //Registers this service with Eureka so other services can find it
@EnableCircuitBreaker

public class RatingsDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingsDataServiceApplication.class, args);
    }

}
