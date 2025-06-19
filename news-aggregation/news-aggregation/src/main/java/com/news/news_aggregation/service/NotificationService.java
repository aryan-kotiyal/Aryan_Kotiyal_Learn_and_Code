package com.news.news_aggregation.service;

import com.news.news_aggregation.dto.NotificationRequest;
import com.news.news_aggregation.model.Notification;

public interface NotificationService {
    void saveOrUpdate(NotificationRequest request, String userEmail);
    Notification getUserConfig(String userEmail);
}
