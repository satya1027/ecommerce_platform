package com.ecommerce.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponse {

    private Long id;
    private String paymentStatus;

}
