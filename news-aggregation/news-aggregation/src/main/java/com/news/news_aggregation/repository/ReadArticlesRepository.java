package com.news.news_aggregation.repository;

import com.news.news_aggregation.model.ReadArticles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadArticlesRepository extends JpaRepository<ReadArticles, Long> {
    public interface UserArticleReadRepository extends JpaRepository<ReadArticles, Long> {
        List<ReadArticles> findByUserId(Integer userId);
    }
}
