package com.news.news_aggregation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "news_categories")

public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean isVisible;

    @ManyToMany(mappedBy = "categories")
    private Set<Keywords> keywords;


    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<NewsArticle> articles = new ArrayList<>();
}
