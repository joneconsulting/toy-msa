package com.example.userservice.error;

public class CustomFeignException extends RuntimeException {
    private final int status;
    private final String body;

    public CustomFeignException(int status, String body) {
        super("Feign error: " + status);
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }
}
