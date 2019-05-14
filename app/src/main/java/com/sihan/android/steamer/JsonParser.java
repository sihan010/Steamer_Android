package com.sihan.android.steamer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class JsonParser {
    public static ArrayList<Movie> MovieJsonToArrayList(String jsondata){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsondata);
            JSONObject data = root.getJSONObject("data");
            JSONArray movies = data.getJSONArray("movies");
            for(int i=0;i<movies.length();i++){
                JSONObject movie = (JSONObject) movies.get(i);
                String title = movie.getString("title");
                String runtime = movie.getString("runtime");
                String year = movie.getString("year");
                String imdb = movie.getString("imdb_code");
                String youtube = movie.getString("yt_trailer_code");
                String image = movie.getString("medium_cover_image");
                String torrent = movie.getString("url");

                Movie movieObject = new Movie(image,title,year,runtime,imdb,youtube,torrent);
                movieArrayList.add(movieObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieArrayList;
    }
}
