package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.Notification;
import com.news.news_aggregation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface NotificationRepository extends JpaRepository<Notification, Long>{
    Optional<Notification> findByUser(User user);
}
