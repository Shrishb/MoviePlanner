package com.movieplanner.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.movieplanner.MainActivity;
import com.movieplanner.R;

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

        startActivity(MainIntent);
    }
}
