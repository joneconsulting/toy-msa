package com.example.catalogservice.dto;

import lombok.Data;

@Data
public class CatalogDto { // implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
