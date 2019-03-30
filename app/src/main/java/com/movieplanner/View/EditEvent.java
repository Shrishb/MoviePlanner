package com.movieplanner.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.movieplanner.Handler.ContactsDataHandler;
import com.movieplanner.Listener.ContactsDataListener;
import com.movieplanner.MainActivity;
import com.movieplanner.Model.Attendees;
import com.movieplanner.R;

import java.util.ArrayList;
import java.util.List;

public class EditEvent extends AppCompatActivity {

    //declare layout items
    private String eventID;
    private  EditText editTitle;
    private  EditText editStartDate;
    private  EditText editEndDate;
    private  EditText editLocation;
    private  EditText editVenue;
    private Button editEventSubmit;
    private EditText attendeesField;
    private List<Attendees> attendees;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        attendees = new ArrayList<>();

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

        // Values in data members

        eventID = newIntent.getStringExtra("eID");

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
        editEventSubmit.setVisibility(View.INVISIBLE);

        attendeesField = (EditText) findViewById(R.id.editEventAttendees);
        attendeesField.setOnClickListener(new ContactsDataListener(this));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
            if (resultCode == RESULT_OK)
            {
                ContactsDataHandler contactsHandler = new ContactsDataHandler(
                        this, data);
                String name = "";
                String email = "";
                try
                {
                    name = contactsHandler.getContactName();
                    email = contactsHandler.getContactEmail();
                }
                catch (ContactsDataHandler.ContactQueryException e)
                {
                }
                attendees.add(new Attendees(name, email));
                updateAttendeesField();
            }
    }


    // update the list with the contact names derived from contact manager
    private void updateAttendeesField()
    {
        List<String> attendeesNames = new ArrayList<>();
        for (Attendees a : attendees)
        {
            attendeesNames.add(a.toString());
        }
        attendeesField.setText(TextUtils.join(", ", attendeesNames));
    }

    //enable all layout items when edit icon is clicked
    public void editDetails(){
        editTitle.setEnabled(true);
        editVenue.setEnabled(true);
        editEndDate.setEnabled(true);
        editStartDate.setEnabled(true);
        editLocation.setEnabled(true);
        editEventSubmit.setVisibility(View.VISIBLE);
    }

    // Method for Edit events
    public void editEvent(View view){

        // MainActivity Loads
        Intent MainIntent = new Intent(EditEvent.this,
                MainActivity.class);

        // killing all previous activities
        MainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Edit Main arraylist after finding the id
        for(int i=0;i<(ListViewFragment.AllEvents.size() -1);i++){
            if(eventID.equals(ListViewFragment.AllEvents.get(i).getEventId())){

                ListViewFragment.AllEvents.get(i).setEventTitle(editTitle.getText().toString());
                // break the loop after changing
                break;
            }
        }
        startActivity(MainIntent);
    }
}

