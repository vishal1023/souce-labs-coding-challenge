package com.soucelab.codingchallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonitoringApplication {

	private static final Logger logger = LoggerFactory.getLogger(MonitoringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MonitoringApplication.class, args);
		logger.info("Monitoring Application started successfully");
	}

}
