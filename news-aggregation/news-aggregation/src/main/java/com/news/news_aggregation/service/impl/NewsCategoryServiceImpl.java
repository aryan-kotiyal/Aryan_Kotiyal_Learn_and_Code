package com.news.news_aggregation.service.impl;

import com.news.news_aggregation.model.NewsCategory;
import com.news.news_aggregation.repository.NewsCategoryRepository;
import com.news.news_aggregation.service.NewsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsCategoryServiceImpl implements NewsCategoryService {
    private final NewsCategoryRepository categoryRepo;

    @Override
    public NewsCategory addCategory(String name) {
        if (categoryRepo.findByName(name).isPresent())
            throw new RuntimeException("Category already exists");

        return categoryRepo.save(NewsCategory.builder().name(name).build());
    }

    @Override
    public List<NewsCategory> getAll() {
        return categoryRepo.findAll();
    }
}
