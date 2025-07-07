package org.news.news_aggregation_client.model;

import java.time.LocalDate;
import java.util.List;

public class NewsArticle {
    private int id;
    private String title;
    private String content;
    private String source;
    private String url;
    private List<NewsCategory> categories;
    private LocalDate publishedAt;


    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getSource() {
        return source;
    }
    public String getUrl() {
        return url;
    }
    public List<NewsCategory> getCategories() { return categories; }

    public String getCategoryNames() {
        if (categories == null || categories.isEmpty()) return "General";
        return categories.stream()
                .map(NewsCategory::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("General");
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

}
