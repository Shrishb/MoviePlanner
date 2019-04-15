package com.movieplanner.Controller.Listener;

import com.movieplanner.Model.Movie;

import java.util.List;

public class Miscelleneaous {

    // Method for getting movie object from string
    public static Movie findMovieObjByID(List<Movie> list, String MovieName){

        Movie singleMovieObj = new Movie();

        for(int i=0;i<list.size();i++){
            if(MovieName.equals(list.get(i).getTitle())){
                singleMovieObj = list.get(i);
            }
        }

        return singleMovieObj;
    }
}
