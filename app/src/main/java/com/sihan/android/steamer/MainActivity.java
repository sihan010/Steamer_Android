package com.sihan.android.steamer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String ENDPOINT = "https://yts.am/api/v2/list_movies.jsonp?sort_by=rating&order_by=desc?linit=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected){
            LinearLayout linear_layout_no_internet = findViewById(R.id.linear_layout_no_internet);
            linear_layout_no_internet.setVisibility(View.GONE);

            MovieListAsyncTask task = new MovieListAsyncTask();
            task.execute(ENDPOINT);
        }
        else{
            ProgressBar progress_main = findViewById(R.id.progress_main);
            progress_main.setVisibility(View.GONE);

            ListView listView_main = findViewById(R.id.list_view_main);
            listView_main.setVisibility(View.GONE);
        }
    }

    private class MovieListAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            String jsonResponse;
            ArrayList<Movie> movieArrayList = new ArrayList<>();
            if (strings[0] != null && !strings[0].isEmpty()) {
                jsonResponse = Utils.getJsonResponse(strings[0]);
                movieArrayList = JsonParser.MovieJsonToArrayList(jsonResponse);
            }
            return movieArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this,R.layout.movie_list_item,movies);

            ListView listView_main = findViewById(R.id.list_view_main);
            listView_main.setAdapter(movieAdapter);

            ProgressBar progress_main = findViewById(R.id.progress_main);
            progress_main.setVisibility(View.GONE);
        }
    }
}
