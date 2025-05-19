package com.example.userservice.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;

    private String orderId;
}
