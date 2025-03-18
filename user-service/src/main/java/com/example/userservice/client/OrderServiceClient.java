package com.example.userservice.client;

import com.example.userservice.error.FeignErrorDecoder;
import com.example.userservice.vo.RequestOrder;
import com.example.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="order-service", configuration = FeignErrorDecoder.class)
public interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders")
//    @GetMapping("/order-service/{userId}/orders_wrong")
//    ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable String userId);
    List<ResponseOrder> getOrders(@PathVariable String userId);

    @PostMapping("/order-service/{userId}/orders")
    ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                              @RequestBody RequestOrder orderDetails);
}
