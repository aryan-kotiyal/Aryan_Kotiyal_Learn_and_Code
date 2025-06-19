package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.SavedArticle;
import com.news.news_aggregation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedArticleRepository extends JpaRepository<SavedArticle, Long> {
    List<SavedArticle> findByUser(User user);
    Optional<SavedArticle> findByUserIdAndArticleId(Long userId, Long articleId);
}
