package com.zaneta.movielibrary.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public  class ResponseConfig {
    public static ResponseEntity getResponse(HttpStatus status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("Message", message);
        return new ResponseEntity<>(response, status);
    }
}
