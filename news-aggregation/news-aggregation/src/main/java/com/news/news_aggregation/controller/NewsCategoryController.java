package com.news.news_aggregation.controller;

import com.news.news_aggregation.model.NewsCategory;
import com.news.news_aggregation.service.NewsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class NewsCategoryController {

    private final NewsCategoryService categoryService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestParam String name) {
        categoryService.addCategory(name);
        return ResponseEntity.ok("Category added.");
    }

    @GetMapping("/all")
    public List<NewsCategory> all() {
        return categoryService.getAll();
    }
}
