package com.news.news_aggregation.controller;

import com.news.news_aggregation.dto.SavedArticleResponse;
import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.model.SavedArticle;
import com.news.news_aggregation.model.User;
import com.news.news_aggregation.repository.NewsArticleRepository;
import com.news.news_aggregation.repository.SavedArticleRepository;
import com.news.news_aggregation.repository.UserRepository;
import com.news.news_aggregation.service.SavedArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user-articles")
@RequiredArgsConstructor
public class SavedArticleController {

    private final SavedArticleRepository savedArticleRepo;
    private final UserRepository userRepo;
    private final NewsArticleRepository articleRepo;
    private final SavedArticleService savedArticleService;

    @PostMapping("/save")
    public ResponseEntity<String> saveArticle(
            @RequestParam Long userId,
            @RequestParam Long articleId
    ) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        NewsArticle article = articleRepo.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        SavedArticle saved = new SavedArticle();
        saved.setUser(user);
        saved.setArticle(article);
        saved.setSavedAt(LocalDateTime.now());

        savedArticleRepo.save(saved);
        return ResponseEntity.ok("Article saved successfully.");
    }

    @GetMapping("/saved")
    public ResponseEntity<List<SavedArticleResponse>> getSavedArticles(@RequestParam Long userId) {
        return ResponseEntity.ok(savedArticleService.getSavedArticles(userId));
    }

    @DeleteMapping("/unsave")
    public ResponseEntity<String> unsaveArticle(@RequestParam Long userId, @RequestParam Long articleId) {
        savedArticleService.deleteSavedArticle(userId, articleId);
        return ResponseEntity.ok("Article unsaved successfully.");
    }
}
