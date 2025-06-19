package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NewsArticleRepository extends JpaRepository<NewsArticle, Long> {
    List<NewsArticle> findByDatePublished(LocalDate date);
    List<NewsArticle> findByDatePublishedBetween(LocalDate startDate, LocalDate endDate);
}
