package org.modak.persistence.repository;

import org.modak.persistence.model.NotificationTypeConfig;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationTypeConfigRepository extends JpaRepository<NotificationTypeConfig, Long> {
    NotificationTypeConfig findNotificationTypeConfigByType(String type);
}
