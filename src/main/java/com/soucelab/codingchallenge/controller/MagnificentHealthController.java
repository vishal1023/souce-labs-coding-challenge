package com.soucelab.codingchallenge.controller;

import com.soucelab.codingchallenge.service.MagnificentHealthCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@EnableScheduling
@RestController
public class MagnificentHealthController {
    private static final Logger logger = LoggerFactory.getLogger(MagnificentHealthController.class);

    private MagnificentHealthCheckService magnificentHealthService;

    public MagnificentHealthController(MagnificentHealthCheckService magnificentHealthService) {
        this.magnificentHealthService = magnificentHealthService;
    }

    @Scheduled(cron = "${health.check.interval}")
    @GetMapping("/magnificent/health")
    public String getHealthOfMagnificent() {
        String health = magnificentHealthService.getHealth();
        logger.info("Health status - " + health);
        return health;
    }

    @GetMapping("/magnificent/health-matrix")
    public Map getHealthMatrix() {
        Map healthMatrix = magnificentHealthService.getHealthMatrix();
        logger.info("Health Matrix For last 100 requests" + healthMatrix);

        return healthMatrix;
    }

    public MagnificentHealthCheckService getMagnificentHealthService() {
        return magnificentHealthService;
    }
}
