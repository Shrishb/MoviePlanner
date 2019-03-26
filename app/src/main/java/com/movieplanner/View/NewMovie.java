package com.movieplanner.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.movieplanner.R;

class NewMovie extends AppCompatActivity {

    //create new movie view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_movie);
    }
}
