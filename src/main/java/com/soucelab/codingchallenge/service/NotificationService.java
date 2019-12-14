package com.soucelab.codingchallenge.service;

import org.springframework.http.HttpStatus;

/**
 * Should Notify Subscribed users about the Health check issue of Monitoring Service
 */
public interface NotificationService {

    void notify(String message, HttpStatus status);
}
