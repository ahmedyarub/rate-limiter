package org.modak.controller;

import org.modak.dto.NotificationRequestDto;
import org.modak.service.RateLimiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@Controller
@RequestMapping("/api/notification")
public class NotificationController {
    final
    RateLimiterService rateLimiterService;

    public NotificationController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @PostMapping("send")
    public ResponseEntity<?> send(@RequestBody NotificationRequestDto requestDto) {
        boolean isAllowed;

        try {
            isAllowed = rateLimiterService.isAllowed(requestDto.getNotificationType());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid type!");
        }

        if (isAllowed) {
            // simulate sending the request

            return ResponseEntity.ok("Notification sent successfully!");
        } else {
            return ResponseEntity.status(TOO_MANY_REQUESTS).body("Please try again later");
        }
    }
}
