package com.movieplanner.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.movieplanner.R;

public class NewMovie extends AppCompatActivity {

    ImageView addMoviePoster;

    //create new movie view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_movie);

        setImagePoster();
    }

    private void setImagePoster(){
        addMoviePoster = (ImageView) findViewById(R.id.addMoviePoster);
        addMoviePoster.setImageResource(R.drawable.poster);
    }
}
