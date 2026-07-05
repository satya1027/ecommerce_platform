package com.ecommerce.payment_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequest {

    private Long orderId;
    private BigDecimal amount;
    private String paymentMethod;

}
