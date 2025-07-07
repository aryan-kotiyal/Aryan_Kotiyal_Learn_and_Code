package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.model.Notification;
import com.news.news_aggregation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    boolean existsByUserAndArticle(User user, NewsArticle article);
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}