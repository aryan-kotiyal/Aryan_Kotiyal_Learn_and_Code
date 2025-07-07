package com.news.news_aggregation.controller;

import com.news.news_aggregation.model.Notification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class NotificationController {

    @GetMapping("/user-history")
    public List<Notification> getUserNotifications(@RequestParam Long userId) {
        return notificationService.getUserNotifications(userId);
    }

}
