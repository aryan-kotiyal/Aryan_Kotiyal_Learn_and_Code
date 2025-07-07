package org.news.news_aggregation_client.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.news.news_aggregation_client.model.Notification;
import org.news.news_aggregation_client.util.HttpClientUtil;

import java.lang.reflect.Type;
import java.util.List;

public class NotificationService {
    public List<Notification> getNotificationHistory(Long userId) {
        try {
            String json = HttpClientUtil.sendGet("http://localhost:8080/api/notifications/user-history?userId=" + userId);
            Type listType = new TypeToken<List<Notification>>() {}.getType();
            return new Gson().fromJson(json, listType);
        } catch (Exception e) {
            System.out.println("Could not fetch notifications: " + e.getMessage());
            return List.of();
        }
    }

}
