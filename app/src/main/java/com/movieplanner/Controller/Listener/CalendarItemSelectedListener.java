package com.movieplanner.Controller.Listener;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.movieplanner.Adapter.CalendarEventAdapter;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;
import com.movieplanner.View.CalendarView;
import com.movieplanner.View.ListViewFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * When attached as the {@code OnItemClickListener} to a Calendar
 * {@code GridView}, pressing a date will populate the given
 * with the events for that date.
 */
public class CalendarItemSelectedListener
        implements AdapterView.OnItemClickListener
{
    private Activity activity;
    private CalendarView calendar;
    private ListView eventList;
    private CalendarEventAdapter calendarEventAdapter;

    //constructor
    public CalendarItemSelectedListener(Activity activity,
                                       CalendarView calendar,
                                       ListView eventList,
                                        CalendarEventAdapter eventArrayAdapter)
    {
        this.activity = activity;
        this.calendar = calendar;
        this.eventList = eventList;
        this.calendarEventAdapter = calendarEventAdapter;
    }

    // on clicking an item it fetches the events present for the particular date.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id)
    {
        Calendar date = (Calendar) parent.getItemAtPosition(position);
        List<MovieEvent> selectedEvents;

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        month = month+1;
        String selectedDate = position + "/" + month + "/" + c.get(Calendar.YEAR);
        selectedEvents =  getAllEventsForSelectedDay(selectedDate);

        calendarEventAdapter = new CalendarEventAdapter(
                activity,
                R.layout.calendar_events_list,
                selectedEvents
        );

        // set custom adapter to set data items of the event
        eventList.setAdapter(calendarEventAdapter);
        eventList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        // on click listener for list item to move to next activity.
        eventList.setOnItemClickListener(new ViewCalendarEventListener(
                activity, calendarEventAdapter, selectedEvents));

        calendar.getCalendarAdapter().setSelectedPosition(position);
        calendar.getCalendarAdapter().notifyDataSetChanged();
    }

    //returns all events for a given day
    public List<MovieEvent> getAllEventsForSelectedDay(String dateVal){
        Date dateToday = new Date();
        List<MovieEvent> allEventsOfDay = new ArrayList<>();
        List<MovieEvent> events = ListViewFragment.AllEvents;

        // loop though the events to check the date and return all events present on the same day.
        Date DateObjCurrentDate = Miscelleneaous.convertStringToDate(dateVal);
        for(int i=0;i<events.size();i++){
            Date reminderDate = Miscelleneaous.convertStringToDate(events.get(i).getStartDate().split(" ")[0]);
            if(reminderDate.compareTo(DateObjCurrentDate) == 0){
                allEventsOfDay.add(events.get(i));
            }
        }
        return allEventsOfDay;
    }
}
