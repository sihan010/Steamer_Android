package com.sihan.android.steamer;

public class Movie {
    private String imageSource;
    private String movieName;
    private String release;
    private String runtime;
    private String imdb;
    private String youtube;
    private String torrent;

    public Movie(String imageSource, String movieName, String release, String runtime, String imdb, String youtube, String torrent) {
        this.imageSource = imageSource;
        this.movieName = movieName;
        this.release = release;
        this.runtime = runtime;
        this.imdb = imdb;
        this.youtube = youtube;
        this.torrent = torrent;
    }

    public String getImageSource() {
        return imageSource;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getRelease() {
        return release;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getImdb() {
        return imdb;
    }

    public String getYoutube() {
        return youtube;
    }

    public String getTorrent() {
        return torrent;
    }
}
