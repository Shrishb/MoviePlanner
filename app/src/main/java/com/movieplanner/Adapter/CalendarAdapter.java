package com.movieplanner.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movieplanner.Controller.Listener.Miscelleneaous;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;
import com.movieplanner.View.ListViewFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class CalendarAdapter extends ArrayAdapter<Calendar>
{
    private Context context;
    private LayoutInflater inflater;
    private int month;
    private int year;
    private Set<Integer> daysWithEvents;
    private int selectedPosition = -1;

    public CalendarAdapter(Context context, List<Calendar> days,
                           int month, int year, Set<Integer> daysWithEvents)
    {
        super(context, R.layout.calendar_day, days);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.month = month;
        this.year = year;
        this.daysWithEvents = daysWithEvents;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.calendar_day, parent, false);
        }

        Calendar date = getItem(position);
        TextView textView = (TextView) convertView.findViewById(
                R.id.calendar_date_text);

        // Don't display days outside of the current month
        if (dateIsInMonth(date))
        {
            textView.setText(String.valueOf(date.get(Calendar.DAY_OF_MONTH)));
        }
        else
        {
            textView.setText("");
        }
    // Highlight event's date
        Calendar today = Calendar.getInstance();
        List<MovieEvent> events = ListViewFragment.AllEvents;

        String currentDate = date.get(Calendar.DAY_OF_MONTH) + "/0"+(date.get(Calendar.MONTH) +1) + "/" + date.get(Calendar.YEAR);

        Date currentDateval = Miscelleneaous.convertStringToDate(currentDate);

        for(int i=0;i<events.size();i++){
            Date eventValue = Miscelleneaous.convertStringToDate(events.get(i).getStartDate().split(" ")[0]);
            if(Miscelleneaous.convertStringToDate(events.get(i).getStartDate()).compareTo(currentDateval) == 0){

                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setTextColor(
                        context.getResources().getColor(R.color.calendar_today));
            }
        }

        // Set selected day
        if (position == selectedPosition)
        {
            convertView.findViewById(R.id.calendar_date_selected)
                    .setVisibility(View.VISIBLE);
        }
        else
        {
            convertView.findViewById(R.id.calendar_date_selected)
                    .setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    public void setSelectedPosition(int position)
    {
        this.selectedPosition = position;
    }

    // check if days outside of the current month
    private boolean dateIsInMonth(Calendar date)
    {
        return (date.get(Calendar.MONTH) == month
                && date.get(Calendar.YEAR) == year);
    }


}
