package com.news.news_aggregation.dto;

import com.news.news_aggregation.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
