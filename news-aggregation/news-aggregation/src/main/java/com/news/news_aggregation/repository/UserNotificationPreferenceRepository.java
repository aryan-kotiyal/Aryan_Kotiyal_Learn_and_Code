package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.NewsCategory;
import com.news.news_aggregation.model.User;
import com.news.news_aggregation.model.UserNotificationPreference;
import com.news.news_aggregation.model.Keywords;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserNotificationPreferenceRepository extends JpaRepository<UserNotificationPreference, Long> {

    List<UserNotificationPreference> findByUser(User user);

    List<UserNotificationPreference> findByUserAndEnabledTrue(User user);

    Optional<UserNotificationPreference> findByUserAndCategoryAndKeyword(User user, NewsCategory category, Keywords keyword);

    List<UserNotificationPreference> findByCategoryAndKeywordAndEnabledTrue(NewsCategory category, Keywords keyword);
}