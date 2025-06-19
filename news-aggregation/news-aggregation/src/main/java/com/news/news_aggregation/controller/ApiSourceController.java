package com.news.news_aggregation.controller;

import com.news.news_aggregation.model.NewsApiSource;
import com.news.news_aggregation.repository.NewsApiSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/apis")
@RequiredArgsConstructor

public class ApiSourceController {
    private final NewsApiSourceRepository sourceRepo;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody NewsApiSource source) {
        sourceRepo.save(source);
        return ResponseEntity.ok("API source saved.");
    }

    @GetMapping
    public List<NewsApiSource> getAll() {
        return sourceRepo.findAll();
    }
}
