package com.news.news_aggregation.controller;

import com.news.news_aggregation.model.NewsArticle;
import com.news.news_aggregation.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/today")
    public List<NewsArticle> getTodayNews() {
        return newsService.getNewsByDate(LocalDate.now());
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
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return newsService.getNewsByDateRange(start, end);
    }
}
