package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {
    Optional<NewsCategory> findByName(String name);
}
