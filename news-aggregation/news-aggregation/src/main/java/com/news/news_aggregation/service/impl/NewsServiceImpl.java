package com.news.news_aggregation.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.news.news_aggregation.model.*;
import com.news.news_aggregation.repository.*;
import com.news.news_aggregation.service.NewsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsApiSourceRepository sourceRepo;
    private final NewsArticleRepository newsRepo;
    private final RestTemplate restTemplate;
    private final NewsCategoryRepository categoryRepo;
    private final UserRepository userRepo;
    private final ReadArticlesRepository readRepo;

    @Override
    @Scheduled(cron = "0 0 0/3 * * *")
    @Transactional
    public void fetchAndSaveNews() {
        List<NewsApiSource> sources = sourceRepo.findAllByOrderByIdAsc();

        for (NewsApiSource source : sources) {
            try {
                String finalUrl = source.getUrl() + (source.getApiKey());
                JsonNode root = restTemplate.getForObject(finalUrl, JsonNode.class);

                List<NewsArticle> articles = source.getName().equalsIgnoreCase("News API")
                        ? parseFromApi1(root)
                        : parseFromApi2(root);

                newsRepo.saveAll(articles);

                source.setActive(true);
                source.setLastAccessedAt(LocalDateTime.now());
                sourceRepo.save(source);
                break;

            } catch (Exception e) {
                e.printStackTrace();
                source.setActive(false);
                sourceRepo.save(source);
            }
        }
    }

    private List<NewsArticle> parseFromApi1(JsonNode root) {
        List<NewsArticle> list = new ArrayList<>();
        for (JsonNode article : root.get("articles")) {
            try {
                String content = article.get("content").asText(null);
                String categoryName = categorize(content);
                NewsCategory category = resolveCategory(categoryName);

                NewsArticle news = NewsArticle.builder()
                        .title(article.get("title").asText())
                        .content(content)
                        .url(article.get("url").asText())
                        .source(article.get("source").get("name").asText())
                        .datePublished(OffsetDateTime.parse(article.get("publishedAt").asText()).toLocalDateTime())
                        .categories(List.of(category))
                        .build();

                list.add(news);
            } catch (Exception e) {
                System.out.println("Failed to parse article: " + article.get("title") + " | Error: " + e.getMessage());
            }
        }
        return list;
    }

    private List<NewsArticle> parseFromApi2(JsonNode root) {
        List<NewsArticle> list = new ArrayList<>();
        for (JsonNode article : root.get("data")) {
            try {
                String rawCategory = article.get("categories").get(1).asText("General");
                NewsCategory category = resolveCategory(rawCategory);

                NewsArticle news = NewsArticle.builder()
                        .title(article.get("title").asText())
                        .content(article.get("description").asText(null))
                        .url(article.get("url").asText())
                        .source(article.get("source").asText())
                        .datePublished(OffsetDateTime.parse(article.get("published_at").asText()).toLocalDateTime())
                        .categories(List.of(category))
                        .build();

                list.add(news);
            } catch (Exception e) {
                System.out.println("Failed to parse article: " + article.get("title") + " | Error: " + e.getMessage());
            }
        }
        return list;
    }

    private NewsCategory resolveCategory(String name) {
        return categoryRepo.findByName(name.trim())
                .orElseGet(() -> categoryRepo.save(NewsCategory.builder().name(name.trim()).build()));
    }

    private String categorize(String text) {
        if (text == null || text.isBlank()) {
            return "General"; // Default fallback
        }
        text = text.toLowerCase();
        if (text.contains("business") || text.contains("market")) return "Business";
        if (text.contains("entertainment") || text.contains("movie") || text.contains("music")) return "Entertainment";
        if (text.contains("sports") || text.contains("cricket") || text.contains("football")) return "Sports";
        if (text.contains("technology") || text.contains("ai") || text.contains("software")) return "Technology";
        return "General";
    }

    @Override
    public List<NewsArticle> getTodaysNews(LocalDate date) {
        LocalDateTime start = date.minusDays(1).atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return newsRepo.findByDatePublishedBetween(start, end);
    }

    @Override
    public List<NewsArticle> getNewsByDatePublished(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return newsRepo.findByDatePublishedBetween(start, end);
    }

    @Override
    public List<NewsArticle> getNewsByCategory(String category) {
        return newsRepo.findAll().stream()
                .filter(n -> n.getCategories() != null &&
                        n.getCategories().stream().anyMatch(cat -> cat.getName().equalsIgnoreCase(category)))
                .toList();
    }

    @Override
    public List<NewsArticle> searchByKeyword(String keyword) {
        return newsRepo.searchByKeyword(keyword);
    }

    @Override
    public List<NewsArticle> getNewsByDateRange(LocalDateTime start, LocalDateTime end) {
        return newsRepo.findByDatePublishedBetween(start, end);
    }

    @Override
    public void markArticleAsRead(Long userId, Long articleId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        NewsArticle article = newsRepo.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        ReadArticles record = ReadArticles.builder()
                .user(user)
                .article(article)
                .readAt(LocalDateTime.now())
                .build();

        readRepo.save(record);
    }

    @Override
    public NewsArticle getArticleById(Long articleId) {
        return newsRepo.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }


}
