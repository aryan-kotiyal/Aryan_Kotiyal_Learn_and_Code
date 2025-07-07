package com.news.news_aggregation.service.impl;

import com.news.news_aggregation.model.*;
import com.news.news_aggregation.repository.*;
import com.news.news_aggregation.service.EmailService;
import com.news.news_aggregation.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserRepository userRepo;
    private final UserNotificationPreferenceRepository preferenceRepo;
    private final NewsArticleRepository articleRepo;
    private final NotificationRepository recordRepo;
    private final EmailService emailService;

    @Override
    @Transactional
    public void processNotifications() {
        List<User> users = userRepo.findAll();
        List<NewsArticle> recentArticles = articleRepo.findByDatePublishedBetween(
                LocalDateTime.now().minusHours(3), LocalDateTime.now());

        for (User user : users) {
            List<UserNotificationPreference> prefs = preferenceRepo.findByUserAndEnabledTrue(user);

            for (UserNotificationPreference pref : prefs) {
                for (NewsArticle article : recentArticles) {
                    boolean categoryMatch = article.getCategories().contains(pref.getCategory());

                    // Category-only enabled
                    if (categoryMatch && pref.getKeyword() == null) {
                        notifyIfNotAlready(user, article);
                    }

                    if (categoryMatch && pref.getKeyword() != null &&
                            (article.getTitle().toLowerCase().contains(pref.getKeyword().getWord().toLowerCase()) ||
                                    article.getContent().toLowerCase().contains(pref.getKeyword().getWord().toLowerCase()))) {
                        notifyIfNotAlready(user, article);
                    }
                    emailService.sendEmail(user.getEmail(),
                            "New Article: " + article.getTitle(),
                            "Check it out: " + article.getUrl());
                }
            }
        }
    }

    private void notifyIfNotAlready(User user, NewsArticle article) {
        if (recordRepo.existsByUserAndArticle(user, article)) {
            return;
        }

        // Save record
        Notification record = Notification.builder()
                .user(user)
                .article(article)
                .createdAt(LocalDateTime.now())
                .build();
        recordRepo.save(record);

        emailService.sendEmail(user.getEmail(),
                "New Article: " + article.getTitle(),
                "Check it out: " + article.getUrl());
    }

    @Override
    public List<Notification> getUserNotifications(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return notificationRepo.findByUserOrderByNotifiedAtDesc(user);
    }

}