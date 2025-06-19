package com.news.news_aggregation.service;

import com.news.news_aggregation.dto.UserLoginRequest;
import com.news.news_aggregation.dto.UserResponse;
import com.news.news_aggregation.dto.UserSignupRequest;

public interface UserService {
    UserResponse signup(UserSignupRequest request);
    UserResponse login(UserLoginRequest request);
}

