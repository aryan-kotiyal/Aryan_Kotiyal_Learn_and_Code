package com.news.news_aggregation.service;

import com.news.news_aggregation.model.NewsArticle;

import java.time.LocalDate;
import java.util.List;

public interface NewsService {
    void fetchAndSaveNews();
    List<NewsArticle> getNewsByDate(LocalDate date);
    List<NewsArticle> getNewsByCategory(String category);
    List<NewsArticle> searchByKeyword(String keyword);
    List<NewsArticle> getNewsByDateRange(LocalDate start, LocalDate end);

}
