package com.news.news_aggregation.controller;

import com.news.news_aggregation.model.NewsApiSource;
import com.news.news_aggregation.model.NewsCategory;
import com.news.news_aggregation.repository.NewsApiSourceRepository;
import com.news.news_aggregation.repository.NewsCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final NewsApiSourceRepository sourceRepo;
    private final NewsCategoryRepository categoryRepo;

    @GetMapping("/servers")
    public List<NewsApiSource> getAllSources() {
        return sourceRepo.findAll();
    }

    @GetMapping("/servers/{id}")
    public ResponseEntity<NewsApiSource> getSourceDetails(@PathVariable Long id) {
        NewsApiSource source = sourceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Source not found"));
        return ResponseEntity.ok(source);
    }

    @PutMapping("/servers/{id}")
    public ResponseEntity<NewsApiSource> updateSource(
            @PathVariable Long id,
            @RequestBody NewsApiSource updated
    ) {
        NewsApiSource existing = sourceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Source not found"));

        existing.setName(updated.getName());
        existing.setUrl(updated.getUrl());
        existing.setApiKey(updated.getApiKey());
        existing.setActive(updated.isActive());

        return ResponseEntity.ok(sourceRepo.save(existing));
    }

    @PostMapping("/categories")
    public ResponseEntity<NewsCategory> addCategory(
            @RequestParam String name,
            @RequestParam(defaultValue = "true") boolean isVisible
    ) {
        if (categoryRepo.findByName(name).isPresent()) {
            throw new RuntimeException("Category already exists");
        }

        NewsCategory category = NewsCategory.builder()
                .name(name.trim())
                .isVisible(isVisible)
                .build();

        return ResponseEntity.ok(categoryRepo.save(category));
    }
}
