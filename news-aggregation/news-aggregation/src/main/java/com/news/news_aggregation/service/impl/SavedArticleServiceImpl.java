package com.news.news_aggregation.service.impl;

import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.model.SavedArticle;
import com.news.news_aggregation.model.User;
import com.news.news_aggregation.repository.NewsArticleRepository;
import com.news.news_aggregation.repository.SavedArticleRepository;
import com.news.news_aggregation.repository.UserRepository;
import com.news.news_aggregation.service.SavedArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavedArticleServiceImpl implements SavedArticleService {

    private final UserRepository userRepo;
    private final NewsArticleRepository newsRepo;
    private final SavedArticleRepository savedRepo;

    @Override
    public void saveArticle(Long userId, Long articleId) {
        User user = userRepo.findById(userId).orElseThrow();
        NewsArticle article = newsRepo.findById(articleId).orElseThrow();

        if (savedRepo.findByUserIdAndArticleId(userId, articleId).isPresent())
            throw new RuntimeException("Already saved");

        SavedArticle saved = SavedArticle.builder()
                .user(user)
                .article(article)
                .savedAt(LocalDateTime.now())
                .build();

        savedRepo.save(saved);
    }

    @Override
    public void deleteSavedArticle(Long userId, Long articleId) {
        SavedArticle saved = savedRepo.findByUserIdAndArticleId(userId, articleId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        savedRepo.delete(saved);
    }

    @Override
    public List<NewsArticle> getSavedArticles(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return savedRepo.findByUser(user).stream()
                .map(SavedArticle::getArticle)
                .collect(Collectors.toList());
    }
}
