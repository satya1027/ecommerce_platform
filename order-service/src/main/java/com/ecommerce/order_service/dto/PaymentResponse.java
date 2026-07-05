package com.ecommerce.order_service.dto;

import lombok.Data;

@Data
public class PaymentResponse {

    private Long id;
    private String paymentStatus;
}
