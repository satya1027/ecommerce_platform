package com.ecommerce.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String status;
    private BigDecimal totalPrice;

}
