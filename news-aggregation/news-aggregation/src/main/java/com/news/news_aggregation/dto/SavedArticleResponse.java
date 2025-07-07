package com.news.news_aggregation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SavedArticleResponse {
    private Long id;
    private String title;
    private String content;
    private String url;
    private String category;
    private String source;
}
