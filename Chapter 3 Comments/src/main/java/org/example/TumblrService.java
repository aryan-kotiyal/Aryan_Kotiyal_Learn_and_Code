package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.example.constants.Messages.NO_DATA_AVAILABLE;

class TumblrService {
    public void displayBlogInfo(JSONObject blogData) {
        if (blogData == null) {
            System.out.println(NO_DATA_AVAILABLE);
            return;
        }

        JSONObject tumblelog = blogData.getJSONObject("tumblelog");
        int totalPosts = blogData.getInt("posts-total");

        System.out.println("\nTitle: " + tumblelog.getString("title"));
        System.out.println("Name: " + tumblelog.getString("name"));
        System.out.println("Description: " + tumblelog.getString("description"));
        System.out.println("No of Posts: " + totalPosts);
    }

    public void displayImageLinks(JSONObject blogData, int start) {
        if (blogData == null) {
            System.out.println(NO_DATA_AVAILABLE);
            return;
        }

        JSONArray posts = blogData.getJSONArray("posts");
        int postNo = start;

        for (int i = 0; i < posts.length(); i++) {
            JSONObject post = posts.getJSONObject(i);
            if (post.has("photo-url-1280")) {
                System.out.println(postNo + ". " + post.getString("photo-url-1280"));
                postNo++;
            }
        }
    }
}
