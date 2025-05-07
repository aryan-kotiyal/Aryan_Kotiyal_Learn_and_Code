package org.example.service;

import org.example.config.AppConfig;
import org.example.config.Messages;
import org.example.model.GeoLocation;
import org.example.util.HttpClientUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GeocodeService {

    public GeoLocation getLocationFromPlace(String place) throws IOException, IllegalArgumentException, RuntimeException {
        String requestUrl = buildRequestUrl(place);
        String response = HttpClientUtil.sendHttpRequest(requestUrl);
        return parseGeoLocation(response);
    }

    private String buildRequestUrl(String place) {
        String encodedPlace = place.replace(" ", "+");
        return String.format("%s?%s=%s&%s=%s",
                AppConfig.GEOCODE_BASE_URL,
                AppConfig.QUERY_PARAM,
                encodedPlace,
                AppConfig.API_KEY_PARAM,
                AppConfig.API_KEY
        );
    }

    private GeoLocation parseGeoLocation(String jsonResponse) {
        JSONArray results = new JSONArray(jsonResponse);
        if (results.isEmpty()) {
            throw new RuntimeException(Messages.ERROR_NO_LOCATION_FOUND);
        }

        JSONObject location = results.getJSONObject(0);
        double lat = Double.parseDouble(location.getString("lat"));
        double lon = Double.parseDouble(location.getString("lon"));
        return new GeoLocation(lat, lon);
    }
}