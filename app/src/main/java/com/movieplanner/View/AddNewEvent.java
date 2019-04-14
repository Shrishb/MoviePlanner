package com.movieplanner.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.movieplanner.Handler.ContactsDataHandler;
import com.movieplanner.Controller.Listener.ContactsDataListener;
import com.movieplanner.Controller.Listener.DatePickerDialogListener;
import com.movieplanner.MainActivity;
import com.movieplanner.Model.Attendees;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;

import java.util.ArrayList;
import java.util.List;

public class AddNewEvent extends AppCompatActivity {

    private EditText attendeesField;
    private List<Attendees> attendees;
    EditText eventTitle ;
    EditText eventStartDate;
    EditText eventEndDate ;
    EditText eventLocation ;
    EditText eventVenue;
    EditText addEventMovie;
    private static final int RES_CODE_A = 3;

    //create new event view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);

        attendees = new ArrayList<>();

        setUiElements();
    }

    //call activity_main intent on save button click
    public void SaveNewEvent(View view){

        Intent MainIntent = new Intent(AddNewEvent.this,
                MainActivity.class);

        // killing all previous activities
        MainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // getting all values of form data after new event submit

        MainIntent.putExtra("EventTitle",eventTitle.getText());
        MainIntent.putExtra("eventStartDate",eventStartDate.getText());
        MainIntent.putExtra("eventEndDate",eventEndDate.getText());
        MainIntent.putExtra("eventLocation",eventLocation.getText());
        MainIntent.putExtra("eventVenue",eventVenue.getText());

        // Making New Event object

        MovieEvent newEvent = new MovieEvent(Integer.toString(ListViewFragment.AllEvents.size() + 1),eventTitle.getText().toString(),
                eventVenue.getText().toString(),
                eventStartDate.getText().toString(),
                eventEndDate.getText().toString(),
                eventLocation.getText().toString() );

        // passing values to FileHandlers

        ListViewFragment listViewFragment = new ListViewFragment();
        listViewFragment.AllEvents.add(0,newEvent);

        startActivity(MainIntent);
    }

    // declare all the textview elements
    private void setUiElements(){

        eventTitle = (EditText) findViewById(R.id.eventTitle);

        eventStartDate = (EditText) findViewById(R.id.eventStartDate);
        new DatePickerDialogListener(this, eventStartDate);

        eventEndDate = (EditText) findViewById(R.id.eventEndDate);
        new DatePickerDialogListener(this, eventEndDate);

        eventLocation = (EditText) findViewById(R.id.eventLocation);
        eventVenue = findViewById(R.id.eventVenue);

        attendeesField = (EditText) findViewById(R.id.addEventAttendees);
        attendeesField.setOnClickListener(new ContactsDataListener(this));

        addEventMovie = (EditText) findViewById(R.id.addEventMovie);
        addEventMovie.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(AddNewEvent.this, ViewMovies.class);
                startActivityForResult(intent, 2);
            }
        });
    }

    //todo: move this code to common to avoid duplicate code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        {
            if (resultCode == RESULT_OK)
            {
                ContactsDataHandler contactsManager = new ContactsDataHandler(
                        this, data);
                String name = "";
                String email = "";
                try
                {
                    name = contactsManager.getContactName();
                    email = contactsManager.getContactEmail();
                }
                catch (ContactsDataHandler.ContactQueryException e)
                {
                }
                attendees.add(new Attendees(name, email));
                updateAttendeesField();
            }
            else if (resultCode == RES_CODE_A){
                    addEventMovie.setText(data.getStringExtra("mName"));
                }
            }
    }

    // fetch all selected contacts and display in the textview
    private void updateAttendeesField()
    {
        List<String> attendeesNames = new ArrayList<>();
        for (Attendees c : attendees)
        {
            System.out.println(c);
            attendeesNames.add(c.toString());
        }
        attendeesField.setText(TextUtils.join(", ", attendeesNames));
    }
}