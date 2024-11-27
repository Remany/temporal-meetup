package ru.aston.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {
    private String name;
    private String customerId;
    private Integer amount;
    private BigDecimal price;
}
