package com.movieplanner.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.movieplanner.Adapter.MoviesAdapter;
import com.movieplanner.Handler.FileHandler;
import com.movieplanner.MainActivity;
import com.movieplanner.Model.Movie;
import com.movieplanner.R;

import java.util.ArrayList;
import java.util.List;

public class ViewMovies extends AppCompatActivity {
    private Context context;
    private List<Movie> list;

    //recyclerview objects
    private RecyclerView moviesRecyclerView;
    private RecyclerView.Adapter adapter;

    //create new event view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_view);
        context = this;

        //initializing views
        moviesRecyclerView = (RecyclerView) findViewById(R.id.moviesRecyclerView);
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        loadRecyclerViewItem();
    }

    private void loadRecyclerViewItem() {
        // call filehandler class method to generate events details in card layout
        FileHandler fileHandler = new FileHandler();
        Movie myList;

        List<Movie> moviesData = fileHandler.parseMoviesFile(context);
        for (int i = 0; i <  moviesData.size(); i++) {
            myList = new Movie(
                    fileHandler.parseMoviesFile(context).get(i).getTitle(),
                    fileHandler.parseMoviesFile(context).get(i).getYear(),
                    fileHandler.parseMoviesFile(context).get(i).getPoster()
            );

            list.add(myList);
        }

        adapter = new MoviesAdapter(list, this);
        moviesRecyclerView.setAdapter(adapter);
    }
}
