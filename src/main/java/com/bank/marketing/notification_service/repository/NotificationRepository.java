package com.bank.marketing.notification_service.repository;

import com.bank.marketing.notification_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
