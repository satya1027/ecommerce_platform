package com.ecommerce.notification_service.service.impl;

import com.ecommerce.notification_service.dto.NotificationRequest;
import com.ecommerce.notification_service.dto.NotificationResponse;
import com.ecommerce.notification_service.entity.Notification;
import com.ecommerce.notification_service.repository.NotificationRepository;
import com.ecommerce.notification_service.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationResponse sendNotification(NotificationRequest request) {

        Notification notification = new Notification();

        notification.setUserId(request.getUserId());
        notification.setEmail(request.getEmail());
        notification.setMessage(request.getMessage());
        notification.setStatus("SENT");
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        Notification saved = notificationRepository.save(notification);

        return new NotificationResponse(
                saved.getId(),
                saved.getStatus()
        );
    }
}
