package com.movieplanner.Model;

import com.movieplanner.Model.Interface.IMovie;

public class Movie implements IMovie {

    private String Id;

    private String title;

    private int Year;

    private String poster;


    public Movie(String id, String title, int year, String poster) {
        Id = id;
        this.title = title;
        Year = year;
        this.poster = poster;
    }


    public String getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return Year;
    }

    public String getPoster() {
        return poster;
    }


}
