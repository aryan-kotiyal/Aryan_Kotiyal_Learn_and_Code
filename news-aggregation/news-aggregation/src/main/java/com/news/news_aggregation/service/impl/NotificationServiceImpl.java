package com.news.news_aggregation.service.impl;


import com.news.news_aggregation.dto.NotificationRequest;
import com.news.news_aggregation.model.Notification;
import com.news.news_aggregation.model.User;
import com.news.news_aggregation.repository.NotificationRepository;
import com.news.news_aggregation.repository.UserRepository;
import com.news.news_aggregation.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository configRepo;
    private final UserRepository userRepo;

    @Override
    public void saveOrUpdate(NotificationRequest request, String email) {
        User user = userRepo.findByEmail(email).orElseThrow();

        Notification config = configRepo.findByUser(user)
                .orElse(Notification.builder().user(user).build());

        config.setKeywords(request.getKeywords());
        config.setCategories(request.getCategories());

        configRepo.save(config);
    }

    @Override
    public Notification getUserConfig(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        return configRepo.findByUser(user).orElse(null);
    }
}
