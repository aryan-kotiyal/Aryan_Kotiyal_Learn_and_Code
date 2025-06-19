package com.news.news_aggregation.service;

import com.news.news_aggregation.model.NewsCategory;

import java.util.List;

public interface NewsCategoryService {
    NewsCategory addCategory(String name);
    List<NewsCategory> getAll();
}
