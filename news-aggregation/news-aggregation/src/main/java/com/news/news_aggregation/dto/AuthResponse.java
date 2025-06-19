package com.news.news_aggregation.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String username;
    private String role;
    private String token;
}
