package com.movieplanner.Controller.Listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.movieplanner.Adapter.CalendarEventAdapter;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.View.EditEvent;

import java.util.List;

public class ViewCalendarEventListener implements AdapterView.OnItemClickListener
{
    private Activity activity;
    private CalendarEventAdapter calendarEventAdapter;
    List<MovieEvent> eventsList;

    //constructor
    public ViewCalendarEventListener(Activity activity, CalendarEventAdapter calendarEventAdapter, List<MovieEvent> selectedEvents)
    {
        this.activity = activity;
        this.calendarEventAdapter = calendarEventAdapter;
        this.eventsList = selectedEvents;
    }

    //call edit event activity on selection of calender list event.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id)
    {
        MovieEvent e = this.eventsList.get(0);

        Intent editIntent = new Intent(activity, EditEvent.class);

        editIntent.putExtra("eID", e.getEventId());
        editIntent.putExtra("eTitle", e.getEventTitle());
        editIntent.putExtra("eStartDate", e.getStartDate());
        editIntent.putExtra("eEndDate", e.getEndDate());
        editIntent.putExtra("eLocation", e.getLocation());
        editIntent.putExtra("eVenue", e.getVenue());

        // movie details
        if(e.getMoviedetails() != null){
            editIntent.putExtra("mTitle", e.getMoviedetails().getTitle());
        }
        else{
            editIntent.putExtra("mTitle", "");
        }

        // contacts details
        String commaSepAttendees = "";
        if(e.getContacts() != null){

            for(int i=0;i<e.getContacts().size();i++){
                commaSepAttendees = e.getContacts().get(i).getEmail() + "," + commaSepAttendees ;
            }

            if(commaSepAttendees != ""){
                editIntent.putExtra("mAttendees", commaSepAttendees.substring(0, commaSepAttendees.length() - 1));
            }
            else{
                editIntent.putExtra("mAttendees", "");
            }
        }
        else{
            editIntent.putExtra("mAttendees", commaSepAttendees);
        }




        activity.startActivity(editIntent);
    }
}
