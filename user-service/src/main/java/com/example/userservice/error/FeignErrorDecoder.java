package com.example.userservice.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    Environment env;

    @Autowired
    public FeignErrorDecoder(Environment env) {
        this.env = env;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        switch(response.status()) {
            case 400:
                break;
            case 404:
                if (methodKey.contains("getOrders")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()),
//                            "User's orders is empty");
                           env.getProperty("order-service.exception.order-is-empty"));
                }
                break;
            case 500:
                if (methodKey.contains("getOrders")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                            "User's orders is empty [Inter Server Error]");
                }
                break;
            case 503:
                if (methodKey.contains("getOrders")) {
                    return new CustomFeignException(response.status(),
                            "User's orders is empty [No order-service available]");
                }
                break;
            default:
                return new Exception(response.reason());
        }

        return null;
    }
}
