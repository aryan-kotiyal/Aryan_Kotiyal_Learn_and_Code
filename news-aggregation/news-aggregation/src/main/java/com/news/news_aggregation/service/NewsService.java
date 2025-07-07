package com.news.news_aggregation.service;

import com.news.news_aggregation.model.NewsArticle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface NewsService {
    void fetchAndSaveNews();
    void markArticleAsRead(Long userId, Long articleId);
    NewsArticle getArticleById(Long articleId);
    List<NewsArticle> getTodaysNews(LocalDate date);
    List<NewsArticle> getNewsByDatePublished(LocalDate date);
    List<NewsArticle> getNewsByCategory(String category);
    List<NewsArticle> searchByKeyword(String keyword);
    List<NewsArticle> getNewsByDateRange(LocalDateTime start, LocalDateTime end);


}
