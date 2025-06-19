package com.news.news_aggregation.service;

import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.model.SavedArticle;

import java.util.List;

public interface SavedArticleService {
    void saveArticle(Long userId, Long articleId);
    void deleteSavedArticle(Long userId, Long articleId);
    List<NewsArticle> getSavedArticles(Long userId);
}
