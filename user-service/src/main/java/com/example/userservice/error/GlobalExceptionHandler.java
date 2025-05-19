package com.example.userservice.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<Map<String, Object>> handleCustomFeignException(CustomFeignException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("code", ex.getStatus());
        error.put("message", "외부 서비스 호출 실패");
        error.put("details", ex.getBody());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
}