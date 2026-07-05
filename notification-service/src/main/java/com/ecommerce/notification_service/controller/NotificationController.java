package com.ecommerce.notification_service.controller;

import com.ecommerce.notification_service.dto.NotificationRequest;
import com.ecommerce.notification_service.dto.NotificationResponse;
import com.ecommerce.notification_service.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public NotificationResponse sendNotification(@RequestBody NotificationRequest request) {
        return notificationService.sendNotification(request);
    }
}
