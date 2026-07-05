package com.ecommerce.notification_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {

    private Long userId;
    private String email;
    private String message;

}
