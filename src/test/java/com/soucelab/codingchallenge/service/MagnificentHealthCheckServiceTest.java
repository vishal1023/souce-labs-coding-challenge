package com.soucelab.codingchallenge.service;

import com.soucelab.codingchallenge.exeption.MagnificentServiceUnhealthyException;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class MagnificentHealthCheckServiceTest {

    @Mock
    private MagnificentHealthServiceGateway magnificentHealthGateway;

    private MagnificentHealthCheckService magnificentHealthCheckService;

    @Before
    public void setUp() {
        magnificentHealthCheckService = new MagnificentHealthCheckService(magnificentHealthGateway);
    }

    @Test
    public void shouldReturnMagnificentServiceHealth() {
        ResponseEntity<String> response = new ResponseEntity<>("Magnificent", OK);
        when(magnificentHealthGateway.health()).thenReturn(response);

        String health = magnificentHealthCheckService.getHealth();

        assertThat(health, Is.is("Magnificent"));
    }

    @Test(expected = MagnificentServiceUnhealthyException.class)
    public void shouldThrowMagnificentServiceUnhealthyException() {
        ResponseEntity<String> response = new ResponseEntity<>("error", INTERNAL_SERVER_ERROR);
        when(magnificentHealthGateway.health()).thenReturn(response);

        magnificentHealthCheckService.getHealth();
    }
}