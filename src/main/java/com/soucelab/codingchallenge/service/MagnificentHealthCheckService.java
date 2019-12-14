package com.soucelab.codingchallenge.service;

import com.soucelab.codingchallenge.exeption.MagnificentServiceUnhealthyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.soucelab.codingchallenge.Constants.*;

@Service
public class MagnificentHealthCheckService {
    private static final Logger logger = LoggerFactory.getLogger(MagnificentHealthCheckService.class);
    private Map<String, Integer> healthMatrix = Stream.of(new Object[][]{
            {SUCCESS, 0},
            {FAIL, 0},
            {UNRESPONSIVE, 0}})
            .collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    private MagnificentHealthServiceGateway magnificentHealthGateway;

    public MagnificentHealthCheckService(MagnificentHealthServiceGateway magnificentHealthGateway) {
        this.magnificentHealthGateway = magnificentHealthGateway;
    }

    public String getHealth() {
        try {
            ResponseEntity responseEntity = magnificentHealthGateway.health();
            handleResponse(responseEntity);
            return handleResponse(responseEntity);
        } catch (Exception e) {
            logger.error("Exception while getting health of service ", e);
            incrementStatusCount(UNRESPONSIVE);
            return "unhealthy";
        }
    }

    public MagnificentHealthServiceGateway getMagnificentHealthGateway() {
        return magnificentHealthGateway;
    }

    private String handleResponse(ResponseEntity responseEntity) {
        logger.info("Magnificent Service is healthy");
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            incrementStatusCount(SUCCESS);
        } else {
            if (responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().is5xxServerError()) {
                logger.warn("Magnificent Service is healthy");
                incrementStatusCount(FAIL);
                throw new MagnificentServiceUnhealthyException();
            }
        }
        return (String) responseEntity.getBody();
    }

    private synchronized void incrementStatusCount(String healthStatus) {
        healthMatrix.put(healthStatus, healthMatrix.get(healthStatus) + 1);
    }

    public Map getHealthMatrix() {
        return healthMatrix;
    }
}
