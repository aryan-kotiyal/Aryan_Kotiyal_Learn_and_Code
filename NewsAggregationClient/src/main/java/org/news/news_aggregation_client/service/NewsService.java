package org.news.news_aggregation_client.service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.news.news_aggregation_client.model.NewsArticle;
import org.news.news_aggregation_client.model.NewsCategory;
import org.news.news_aggregation_client.util.HttpClientUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NewsService {
    private static final String BASE_URL = "http://localhost:8080/api/";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                    return LocalDate.parse(json.getAsString());
                }
            })
            .create();


    public List<NewsArticle> getTodaysNews() {
        try {
            String url = BASE_URL + "news/today";
            String json = HttpClientUtil.sendGet(url);
            Type listType = new TypeToken<List<NewsArticle>>() {}.getType();
            return gson.fromJson(json, listType);
        } catch (Exception e) {
            System.out.println("Failed to fetch today's news: " + e.getMessage());
            return List.of();
        }
    }

    public NewsArticle readFullArticle(Long articleId, Long userId) {
        try {
            String url = BASE_URL + "user-articles/article/" + articleId;
            String json = HttpClientUtil.sendGet(url);
            NewsArticle article = gson.fromJson(json, NewsArticle.class);

            String endpoint = BASE_URL + "user-articles/read?userId=" + userId + "&articleId=" + articleId;
            HttpClientUtil.sendPost(endpoint, "");

            return article;
        } catch (Exception e) {
            System.out.println("Could not read article: " + e.getMessage());
            return null;
        }
    }

    public void saveArticle(long articleId, long userId) {
        try {
            String url = BASE_URL + "user-articles/save?userId=" + userId + "&articleId=" + articleId;
            String response = HttpClientUtil.sendPost(url, "");
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("Could not save article: " + e.getMessage());
        }
    }

    public void unsaveArticle(long userId, long articleId) {
        try {
            String url = BASE_URL + "user-articles/unsave?userId=" + userId + "&articleId=" + articleId;
            HttpClientUtil.sendDelete(url);
            System.out.println("Article unsaved successfully.");
        } catch (Exception e) {
            System.out.println("Could not unsave article: " + e.getMessage());
        }
    }

    public List<NewsArticle> getSavedArticles(long userId) {
        try {
            String url = BASE_URL + "user-articles/saved?userId=" + userId;
            String json = HttpClientUtil.sendGet(url);
            Type listType = new TypeToken<List<NewsArticle>>() {}.getType();
            return gson.fromJson(json, listType);
        } catch (Exception e) {
            System.out.println("Could not fetch saved articles: " + e.getMessage());
            return List.of();
        }
    }

    public void getNewsByDateRangeAndCategory(LocalDateTime start, LocalDateTime end, String category) {
        try {
            String startStr = URLEncoder.encode(start.toString(), StandardCharsets.UTF_8);
            String endStr = URLEncoder.encode(end.toString(), StandardCharsets.UTF_8);
            String url = BASE_URL + "news/range?start=" + startStr + "&end=" + endStr;

            String json = HttpClientUtil.sendGet(url);
            Type listType = new TypeToken<List<NewsArticle>>() {}.getType();
            List<NewsArticle> articles = gson.fromJson(json, listType);

            List<NewsArticle> filtered = articles.stream()
                    .filter(a -> category.equalsIgnoreCase("All") ||
                            a.getCategories().stream().anyMatch(c -> c.getName().equalsIgnoreCase(category)))
                    .toList();

            System.out.println("\n____________________________________________________");
            System.out.println("H E A D L I N E S  (" + category + ")");
            System.out.println("____________________________________________________");

            if (filtered.isEmpty()) {
                System.out.println("No articles found.");
            } else {
                for (NewsArticle article : filtered) {
                    System.out.println("Article Id: " + article.getId());
                    System.out.println(article.getTitle());
                    System.out.println(article.getContent());
                    System.out.println("Source: " + article.getSource());
                    System.out.println("URL: " + article.getUrl());
                    System.out.println("Category: " + article.getCategoryNames());
                    System.out.println("____________________________________________________");
                }
            }

        } catch (Exception e) {
            System.out.println("Error fetching articles: " + e.getMessage());
        }
    }

    public List<NewsArticle> searchArticles(String keyword) {
        try {
            String url = BASE_URL + "news/search?keyword=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            String json = HttpClientUtil.sendGet(url);

            Type listType = new TypeToken<List<NewsArticle>>() {}.getType();
            List<NewsArticle> articles = gson.fromJson(json, listType);
            return articles;
        } catch (IOException e) {
            System.out.println("Error while searching articles: " + e.getMessage());
            return List.of();
        }
    }

    public List<String> getVisibleCategories() {
        try {
            String url = BASE_URL + "categories/visible";
            String json = HttpClientUtil.sendGet(url);
            Type listType = new TypeToken<List<NewsCategory>>() {}.getType();
            List<NewsCategory> categories = gson.fromJson(json, listType);
            return categories.stream().map(NewsCategory::getName).toList();
        } catch (Exception e) {
            System.out.println("Error fetching categories: " + e.getMessage());
            return List.of();
        }
    }

}
