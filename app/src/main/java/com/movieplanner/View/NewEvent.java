package com.movieplanner.View;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.movieplanner.R;

public class NewEvent extends Activity {

    //create new event view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        EditText newEventLabel = findViewById(R.id.newEventLabel);
        newEventLabel.setEnabled(false);
    }
}
