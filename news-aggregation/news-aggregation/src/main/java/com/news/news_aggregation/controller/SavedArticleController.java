package com.news.news_aggregation.controller;

import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.model.User;
import com.news.news_aggregation.repository.UserRepository;
import com.news.news_aggregation.service.SavedArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/api/saved")
@RequiredArgsConstructor
public class SavedArticleController {

    private final SavedArticleService savedService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestParam Long articleId) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = "testuser@email.com";
        User user = userRepository.findByEmail(email).orElseThrow();
        savedService.saveArticle(user.getId(), articleId);
        return ResponseEntity.ok("Article saved!");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam Long articleId) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = "testuser@email.com";
        User user = userRepository.findByEmail(email).orElseThrow();
        savedService.deleteSavedArticle(user.getId(), articleId);
        return ResponseEntity.ok("Article removed!");
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all")
    public List<NewsArticle> getSaved() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = "testuser@email.com";
        User user = userRepository.findByEmail(email).orElseThrow();
        return savedService.getSavedArticles(user.getId());
    }
}
