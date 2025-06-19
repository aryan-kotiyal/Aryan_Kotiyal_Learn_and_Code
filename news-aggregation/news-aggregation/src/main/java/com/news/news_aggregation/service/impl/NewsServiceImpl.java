package com.news.news_aggregation.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.news.news_aggregation.model.NewsApiSource;
import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.repository.NewsApiSourceRepository;
import com.news.news_aggregation.repository.NewsArticleRepository;
import com.news.news_aggregation.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsApiSourceRepository sourceRepo;
    private final NewsArticleRepository newsRepo;
    private final RestTemplate restTemplate;

//    @Value("${news.api.key}")
//    private String apiKey;

//    @Override
//    @Scheduled(cron = "0 0 */3 * * *") // Every 3 hours
//    public void fetchAndSaveNews() {
//        String url = "https://newsapi.org/v2/top-headlines?country=us&pageSize=10&apiKey=" + apiKey;
//
//        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
//        if (response == null || !response.has("articles")) return;
//
//        for (JsonNode item : response.get("articles")) {
//            String title = item.get("title").asText();
//            String content = item.get("description") != null ? item.get("description").asText() : "";
//            String source = item.get("source").get("name").asText();
//            String category = categorize(title + " " + content);
//
//            NewsArticle article = NewsArticle.builder()
//                    .title(title)
//                    .content(content)
//                    .category(category)
//                    .source(source)
//                    .datePublished(LocalDate.now())
//                    .likes(0)
//                    .dislikes(0)
//                    .build();
//
//            newsRepo.save(article);
//        }
//    }
    @Override
    @Scheduled(cron = "0 0 0/3 * * *")
    public void fetchAndSaveNews() {
        List<NewsApiSource> sources = sourceRepo.findAllByOrderByIdAsc();

        for (NewsApiSource source : sources) {
            try {
                String finalUrl = source.getUrl()+(source.getApiKey());
                JsonNode root = restTemplate.getForObject(finalUrl, JsonNode.class);
                List<NewsArticle> parsed = source.getName().equalsIgnoreCase("News API")
                        ? parseFromApi1(root)
                        : parseFromApi2(root);

                newsRepo.saveAll(parsed);

                source.setActive(true);
                sourceRepo.save(source);

                break;
            } catch (Exception e) {
                source.setActive(false);
                sourceRepo.save(source);
            }
        }
    }

    private String categorize(String text) {
        text = text.toLowerCase();
        if (text.contains("business") || text.contains("market")) return "Business";
        if (text.contains("entertainment") || text.contains("movie") || text.contains("music")) return "Entertainment";
        if (text.contains("sports") || text.contains("cricket") || text.contains("football")) return "Sports";
        if (text.contains("technology") || text.contains("AI") || text.contains("software")) return "Technology";
        return "General";
    }

    @Override
    public List<NewsArticle> getNewsByDate(LocalDate date) {
        return newsRepo.findByDatePublished(date);
    }

    @Override
    public List<NewsArticle> getNewsByCategory(String category) {
        return newsRepo.findAll().stream()
                .filter(n -> n.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @Override
    public List<NewsArticle> searchByKeyword(String keyword) {
        return newsRepo.findAll().stream()
                .filter(n -> n.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        n.getContent().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public List<NewsArticle> getNewsByDateRange(LocalDate start, LocalDate end) {
        return newsRepo.findByDatePublishedBetween(start, end);
    }

    private List<NewsArticle> parseFromApi1(JsonNode root) {
        List<NewsArticle> list = new ArrayList<>();
        for (JsonNode article : root.get("articles")) {
            NewsArticle news = NewsArticle.builder()
                    .title(article.get("title").asText())
                    .content(article.get("description").asText(null))
                    .url(article.get("url").asText())
                    .source(article.get("source").get("name").asText())
                    .datePublished(LocalDate.parse(article.get("publishedAt").asText().replace("Z", "")))
                    .category("General")
                    .build();
            list.add(news);
        }
        return list;
    }

    private List<NewsArticle> parseFromApi2(JsonNode root) {
        List<NewsArticle> list = new ArrayList<>();
        for (JsonNode article : root.get("data")) {
            NewsArticle news = NewsArticle.builder()
                    .title(article.get("title").asText())
                    .content(article.get("description").asText(null))
                    .url(article.get("url").asText())
                    .source(article.get("source").asText())
                    .datePublished(LocalDate.parse(article.get("published_at").asText().replace("Z", "")))
                    .category(article.get("categories").get(0).asText("General"))
                    .build();
            list.add(news);
        }
        return list;
    }

}
