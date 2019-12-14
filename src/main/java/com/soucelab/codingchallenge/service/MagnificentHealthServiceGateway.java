package com.soucelab.codingchallenge.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MagnificentHealthServiceGateway {

    public ResponseEntity<String> health() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String response = restTemplate.getForObject(
                "http://localhost:12345/", String.class);

        System.out.println("response = " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
