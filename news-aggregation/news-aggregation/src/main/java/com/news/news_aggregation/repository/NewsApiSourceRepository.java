package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.NewsApiSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsApiSourceRepository extends JpaRepository<NewsApiSource, Long> {
    List<NewsApiSource> findAllByOrderByIdAsc();
}
