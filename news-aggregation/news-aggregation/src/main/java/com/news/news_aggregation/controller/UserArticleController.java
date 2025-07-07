package com.news.news_aggregation.controller;

import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-articles")
@RequiredArgsConstructor
public class UserArticleController {

    private final NewsService newsService;

    @PostMapping("/read")
    public ResponseEntity<Void> markAsRead(@RequestParam Long userId, @RequestParam Long articleId) {
        newsService.markArticleAsRead(userId, articleId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<NewsArticle> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getArticleById(id));
    }
}
