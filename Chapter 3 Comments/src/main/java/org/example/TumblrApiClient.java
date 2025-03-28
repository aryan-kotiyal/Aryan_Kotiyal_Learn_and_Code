package org.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

class TumblrAPIClient {

    public static JSONObject fetchBlogData(String blogName, int start, int num) {
        try {
            String urlString = "https://" + blogName + ".tumblr.com/api/read/json?type=photo&num=" + num + "&start=" + start;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            //we replace "var tumblr_api_read = " with blank space to remove the error while parsing JSON response
            String jsonResponse = response.toString().replace("var tumblr_api_read = ", "").trim();
            return new JSONObject(jsonResponse);
        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
            return null;
        }
    }
}