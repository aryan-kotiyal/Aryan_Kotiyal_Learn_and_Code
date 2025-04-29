package org.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.example.model.GeoLocation;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeocodeService {
    private static final String API_KEY = "6810c6459fdd2197899117rov3a11b6";
    private static final String BASE_URL = "https://geocode.maps.co/search";

    public GeoLocation getLocationFromPlace(String place) throws Exception {
        String url = BASE_URL + "?q=" + place.replace(" ", "+") + "&api_key=" + API_KEY;
        String response = sendHttpRequest(url);
        return parseGeoLocation(response);
    }

    private String sendHttpRequest(String requestUrl) throws Exception {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code " + connection.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuffer = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseBuffer.append(line);
        }
        reader.close();
        return responseBuffer.toString();
    }

    private GeoLocation parseGeoLocation(String jsonResponse) {
        JSONArray results = new JSONArray(jsonResponse);
        if (results.isEmpty()) {
            throw new RuntimeException("No location found.");
        }

        JSONObject location = results.getJSONObject(0);
        double lat = Double.parseDouble(location.getString("lat"));
        double lon = Double.parseDouble(location.getString("lon"));
        return new GeoLocation(lat, lon);
    }
}