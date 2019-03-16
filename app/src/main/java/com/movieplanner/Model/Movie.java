package com.movieplanner.Model;

import com.movieplanner.Model.Interface.IMovie;

public class Movie implements IMovie {

    private String Id;

    private String title;

    private String Year;

    private String poster;


    public Movie(String id, String title, String year, String poster) {
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

    public String getYear() {
        return Year;
    }

    public String getPoster() {
        return poster;
    }


}
