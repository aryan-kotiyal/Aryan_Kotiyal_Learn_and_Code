package org.news.news_aggregation_client.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import org.news.news_aggregation_client.model.NewsApiSource;
import org.news.news_aggregation_client.util.HttpClientUtil;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

public class AdminService {
    private static final String BASE_URL = "http://localhost:8080/api/admin";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, type, context) -> LocalDateTime.parse(json.getAsString()))
            .create();


    public List<NewsApiSource> getAllApiSources() {
        try {
            String json = HttpClientUtil.sendGet(BASE_URL + "/servers");
            Type listType = new TypeToken<List<NewsApiSource>>() {}.getType();
            return gson.fromJson(json, listType);
        } catch (Exception e) {
            System.out.println("Failed to fetch server list: " + e.getMessage());
            return List.of();
        }
    }

    public NewsApiSource getApiSourceDetails(long id) {
        try {
            String json = HttpClientUtil.sendGet(BASE_URL + "/servers/" + id);
            return gson.fromJson(json, NewsApiSource.class);
        } catch (Exception e) {
            System.out.println("Failed to fetch server details: " + e.getMessage());
            return null;
        }
    }

    public void updateApiSource(NewsApiSource updated) {
        try {
            String json = gson.toJson(updated);
            HttpClientUtil.sendPut(BASE_URL + "/servers/" + updated.getId(), json);
            System.out.println("Server details updated.");
        } catch (Exception e) {
            System.out.println("Failed to update server: " + e.getMessage());
        }
    }

    public void addCategory(String categoryName) {
        try {
            String encoded = URLEncoder.encode(categoryName, StandardCharsets.UTF_8);
            HttpClientUtil.sendPost(BASE_URL + "/categories?name=" + encoded, "");
            System.out.println("Category added successfully.");
        } catch (Exception e) {
            System.out.println("Failed to add category: " + e.getMessage());
        }
    }
}