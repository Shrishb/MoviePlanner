package com.movieplanner.Model;

import com.movieplanner.Model.Interface.IMovie;

public class Movie implements IMovie {

    private String movieId;

    private String title;

    private String Year;

    private String poster;


    public Movie( String iD, String title, String year, String poster) {
        this.movieId = iD;
        this.title = title;
        Year = year;
        this.poster = poster;
    }

    public Movie( String title, String year, String poster) {
        this.title = title;
        this.Year = year;
        this.poster = poster;
        this.movieId = title.substring(0,3) + year;
    }

    // default constructor
    public Movie(){}


    public String getId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return Year;
    }

    public String getPoster() {
        return poster;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
