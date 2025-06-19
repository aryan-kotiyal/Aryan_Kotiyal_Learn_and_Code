package com.news.news_aggregation.controller;

import com.news.news_aggregation.dto.NotificationRequest;
import com.news.news_aggregation.model.Notification;
import com.news.news_aggregation.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/config")
    public ResponseEntity<String> configure(@RequestBody NotificationRequest request) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = "testuser@email.com";
        notificationService.saveOrUpdate(request, email);
        return ResponseEntity.ok("Notification preferences saved.");
    }

    @GetMapping("/config")
    public Notification getConfig() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = "testuser@email.com";
        return notificationService.getUserConfig(email);
    }
}
