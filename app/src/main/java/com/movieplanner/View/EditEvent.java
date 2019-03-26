package com.movieplanner.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.movieplanner.R;

public class EditEvent extends AppCompatActivity {

    //declare layout items
    private  EditText editTitle;
    private  EditText editStartDate;
    private  EditText editEndDate;
    private  EditText editLocation;
    private  EditText editVenue;
    private Button editEventSubmit;

    // bind layout menu on top to display edit icons
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actions_view_event, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event);

        setAllFields();
    }

    // handle the options button click
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.view_event_edit:
                editDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // set al layout items as disabled upon loading the activity
    private void setAllFields(){
        Intent newIntent = getIntent();

        editTitle = findViewById(R.id.editEventTitle);
        editTitle.setText(newIntent.getStringExtra("eTitle"));
        editTitle.setEnabled(false);

        editStartDate = findViewById(R.id.editEventStartDate);
        editStartDate.setText(newIntent.getStringExtra("eStartDate"));
        editStartDate.setEnabled(false);

        editEndDate = findViewById(R.id.editEventEndDate);
        editEndDate.setText(newIntent.getStringExtra("eEndDate"));
        editEndDate.setEnabled(false);

        editVenue = findViewById(R.id.editEventVenue);
        editVenue.setText(newIntent.getStringExtra("eVenue"));
        editVenue.setEnabled(false);

        editLocation = findViewById(R.id.editEventLocation);
        editLocation.setText(newIntent.getStringExtra("eLocation"));
        editLocation.setEnabled(false);

        editEventSubmit = findViewById(R.id.editEventSubmit);
        editEventSubmit.setEnabled(false);
    }

    //enable all layout items when edit icon is clicked
    public void editDetails(){
        editTitle.setEnabled(true);
        editVenue.setEnabled(true);
        editEndDate.setEnabled(true);
        editStartDate.setEnabled(true);
        editLocation.setEnabled(true);
        editEventSubmit.setEnabled(true);
    }
}

