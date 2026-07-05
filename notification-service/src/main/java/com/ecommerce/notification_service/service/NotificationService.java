package com.ecommerce.notification_service.service;

import com.ecommerce.notification_service.dto.NotificationRequest;
import com.ecommerce.notification_service.dto.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest request);

}
