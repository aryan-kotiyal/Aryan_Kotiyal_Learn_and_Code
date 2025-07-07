package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface NewsArticleRepository extends JpaRepository<NewsArticle, Long> {
    List<NewsArticle> findByDatePublishedBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT a FROM NewsArticle a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<NewsArticle> searchByKeyword(@Param("keyword") String keyword);
}
