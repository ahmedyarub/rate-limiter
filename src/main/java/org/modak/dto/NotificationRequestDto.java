package org.modak.dto;

import lombok.Data;

@Data
public class NotificationRequestDto {
    String notificationType;
    String user;
    String text;
}
