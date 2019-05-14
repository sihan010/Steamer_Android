package com.sihan.android.steamer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private int mResource;
    private Context mContext;

    public MovieAdapter(Context context, int resource, ArrayList<Movie> movies) {
        super(context, resource, movies);
        this.mContext = context;
        this.mResource = resource;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        final Movie item = getItem(position);

        ImageView image_movie_banner = convertView.findViewById(R.id.image_movie_banner);
        TextView text_view_movie_name = convertView.findViewById(R.id.text_view_movie_name);
        TextView text_view_release = convertView.findViewById(R.id.text_view_release);
        TextView text_view_runtime = convertView.findViewById(R.id.text_view_runtime);
        ImageView image_imdb = convertView.findViewById(R.id.image_imdb);
        ImageView image_youtube = convertView.findViewById(R.id.image_youtube);
        ImageView image_torrent = convertView.findViewById(R.id.image_torrent);
        ImageView image_stream = convertView.findViewById(R.id.image_stream);

        Picasso.get().load(item.getImageSource()).into(image_movie_banner);
        text_view_movie_name.setText(item.getMovieName());
        text_view_release.setText("Release Year: "+item.getRelease());
        text_view_runtime.setText("Runtime: "+item.getRuntime()+" minutes");

        image_imdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.imdb.com/title/"+item.getImdb()));
                mContext.startActivity(intent);
            }
        });

        image_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/embed/"+item.getYoutube()));
                mContext.startActivity(intent);
            }
        });

        image_torrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(item.getTorrent()));
                mContext.startActivity(intent);
            }
        });

        image_stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Feature Under Development",Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
