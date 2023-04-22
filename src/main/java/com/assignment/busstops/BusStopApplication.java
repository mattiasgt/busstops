package com.assignment.busstops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BusStopApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BusStopApplication.class, args);
	}
}
