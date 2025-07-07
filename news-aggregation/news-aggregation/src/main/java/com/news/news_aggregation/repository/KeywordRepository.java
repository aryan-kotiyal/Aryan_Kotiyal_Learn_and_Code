package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.Keywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keywords, Long> {
    Optional<Keywords> findByWord(String word);
}