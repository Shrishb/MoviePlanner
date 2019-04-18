package com.movieplanner.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;

import java.util.List;

/**
 * Adapter for use with calendar events in calendar view
 */
public class CalendarEventAdapter extends ArrayAdapter<MovieEvent>
{
    private Context context;
    private int layoutResource;
    List<MovieEvent> selectedEvents;

    //constructor
    public CalendarEventAdapter(Context context, int resource,List<MovieEvent> events)
    {
        super(context, resource, events);
        this.context = context;
        this.layoutResource = resource;
        this.selectedEvents = events;
    }

    // get the listview item and display it in the layout
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
                if (view == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResource, parent, false);
        }

       for (int i =0 ; i< this.selectedEvents.size() ; i++) {

           MovieEvent event = this.selectedEvents.get(i);

           TextView eventTitle = (TextView) view.findViewById(R.id.calendarEventTitle);
           eventTitle.setText(event.getEventTitle());

           TextView eventVenue = (TextView) view.findViewById(R.id.calendarEventVenue);
           eventVenue.setText(event.getVenue());

       }

        return view;
    }


        @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }
}
