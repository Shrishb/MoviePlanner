package com.movieplanner.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.movieplanner.Handler.FileHandler;
import com.movieplanner.MainActivity;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;

import java.io.File;

public class NewEvent extends AppCompatActivity {



    //create new event view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        // EditText newEventLabel = findViewById(R.id.newEventLabel);
        //newEventLabel.setEnabled(false);


    }

    //call main intent on save button click
    public void SaveNewEvent(View view){

        Intent MainIntent = new Intent(NewEvent.this,
                MainActivity.class);

        // killing all previous activities
        MainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // getting all values of form data after new event submit

        EditText eventTitle = (EditText) findViewById(R.id.eventTitle);

        EditText eventStartDate = (EditText) findViewById(R.id.eventStartDate);

        EditText eventEndDate = (EditText) findViewById(R.id.eventEndDate);

        EditText eventLocation = (EditText) findViewById(R.id.eventLocation);

        EditText eventVenue = findViewById(R.id.eventVenue);


        MainIntent.putExtra("EventTitle",eventTitle.getText());

        MainIntent.putExtra("eventStartDate",eventStartDate.getText());

        MainIntent.putExtra("eventEndDate",eventEndDate.getText());
        MainIntent.putExtra("eventLocation",eventLocation.getText());
        MainIntent.putExtra("eventVenue",eventVenue.getText());

        // Making New Event object

        MovieEvent newEvent = new MovieEvent("3",eventTitle.getText().toString(),  eventVenue.getText().toString(), eventLocation.getText().toString() );

        // passing values to FileHandlers

        FileHandler newEventvals = new FileHandler();
         newEventvals.newEvent = newEvent;

         newEventvals.parseEventsFile(this);

        startActivity(MainIntent);
    }

}