package com.news.news_aggregation.dto;

import lombok.Data;

@Data
public class UserSignupRequest {
    private String username;
    private String email;
    private String password;
}
