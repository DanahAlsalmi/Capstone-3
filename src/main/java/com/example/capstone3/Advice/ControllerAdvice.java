package com.example.capstone3.Advice;

import com.example.capstone3.Api.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity ApiException(ApiException apiException) {
        return ResponseEntity.status(400).body(apiException.getMessage());
    }
}
