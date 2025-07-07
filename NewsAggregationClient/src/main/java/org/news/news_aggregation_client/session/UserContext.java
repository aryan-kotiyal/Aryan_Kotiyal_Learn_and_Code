package org.news.news_aggregation_client.session;

public class UserContext {
    private static Long userId;
    private static String username;

    public static void setUser(Long id, String name) {
        userId = id;
        username = name;
    }

    public static Long getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static void clear() {
        userId = null;
        username = null;
    }
}
