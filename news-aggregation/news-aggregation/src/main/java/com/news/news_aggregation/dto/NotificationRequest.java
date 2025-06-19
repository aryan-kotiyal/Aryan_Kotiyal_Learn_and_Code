package com.news.news_aggregation.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotificationRequest {
    private List<String> keywords;
    private List<String> categories;
}
