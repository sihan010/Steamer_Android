package com.sihan.android.steamer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public abstract class Utils {
    public static String getJsonResponse(String endpoint) {
        String jsonResponse;
        URL url = getUrl(endpoint);
        if (url != null) {
            jsonResponse = fetchDataFromInternet(url);
        } else return "";
        return jsonResponse;
    }

    private static String fetchDataFromInternet(URL url) {
        InputStream inputStream ;
        String jsonResponse = "";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    private static String readFromInputStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream!=null){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    private static URL getUrl(String endpoint) {
        URL url = null;
        try {
            url = new URL(endpoint);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            return url;
        }
    }
}
