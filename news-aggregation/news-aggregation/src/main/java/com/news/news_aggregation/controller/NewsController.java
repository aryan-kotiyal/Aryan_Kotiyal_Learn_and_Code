package com.news.news_aggregation.controller;

import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/today")
    public List<NewsArticle> getTodayNews() {
        return newsService.getTodaysNews(LocalDate.now());
    }

    @GetMapping("/date-published")
    public List<NewsArticle> getNewsByExactDate(@RequestParam LocalDate date) {
        return newsService.getNewsByDatePublished(date);
    }

    @GetMapping("/category/{category}")
    public List<NewsArticle> getByCategory(@PathVariable String category) {
        return newsService.getNewsByCategory(category);
    }

    @GetMapping("/search")
    public List<NewsArticle> search(@RequestParam String keyword) {
        return newsService.searchByKeyword(keyword);
    }

    @GetMapping("/range")
    public List<NewsArticle> getNewsBetweenDates(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime end) {
        return newsService.getNewsByDateRange(start, end);
    }
}
