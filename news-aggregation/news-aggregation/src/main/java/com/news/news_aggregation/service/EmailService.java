package com.news.news_aggregation.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}