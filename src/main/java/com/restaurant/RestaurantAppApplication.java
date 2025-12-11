package com.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.restaurant"})
public class RestaurantAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantAppApplication.class, args);
	}

}
