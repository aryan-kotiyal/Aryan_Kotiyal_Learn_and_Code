package org.example.util;

import org.example.config.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientUtil {

    public static String sendHttpRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != HttpStatus.OK.getCode()) {
            throw new RuntimeException(Messages.ERROR_HTTP_FAILURE + connection.getResponseCode());
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
}
