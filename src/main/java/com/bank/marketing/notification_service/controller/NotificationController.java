package com.bank.marketing.notification_service.controller;

import com.bank.marketing.notification_service.model.Notification;
import com.bank.marketing.notification_service.model.NotificationRequest;
import com.bank.marketing.notification_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }



    @GetMapping
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        // Process the request and send a notification
        return ResponseEntity.ok("Notification sent to:"+ request.getRecipient());
    }
}

