package org.news.news_aggregation_client.service;

import org.news.news_aggregation_client.model.UserLoginRequest;
import org.news.news_aggregation_client.model.UserResponse;
import org.news.news_aggregation_client.model.UserSignupRequest;
import org.news.news_aggregation_client.session.UserContext;
import org.news.news_aggregation_client.util.HttpClientUtil;

import com.google.gson.JsonObject;
import com.google.gson.Gson;


public class AuthService {
    private static final String BASE_URL = "http://localhost:8080/api/auth";
    private final Gson gson = new Gson();

    public void signup(UserSignupRequest request) {
        try {
            String json = gson.toJson(request);
            String response = HttpClientUtil.sendPost(BASE_URL + "/signup", json);
            System.out.println("Signup successful!\n" + response);
        } catch (Exception e) {
            System.out.println("Signup failed: " + e.getMessage());
        }
    }

    public String login(UserLoginRequest request) {
        try {
            String json = gson.toJson(request);
            String response = HttpClientUtil.sendPost(BASE_URL + "/login", json);
            UserResponse user = gson.fromJson(response, UserResponse.class);
            System.out.println("Login successful as " + user.getRole() + "!");
            UserContext.setUser(user.getId(), user.getUsername());
            return user.getRole();
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }
}
