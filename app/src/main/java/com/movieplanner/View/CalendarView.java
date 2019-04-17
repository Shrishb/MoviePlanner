package com.movieplanner.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.movieplanner.Adapter.CalendarAdapter;
import com.movieplanner.Controller.Listener.Miscelleneaous;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;


public class CalendarView extends LinearLayout
{

    private final DateFormat MONTH_FORMAT = new SimpleDateFormat(
            "MMMM yyyy", Locale.getDefault());
    private Calendar displayedMonth;
    private TextView monthHeader;
    private ImageView prevBtn;
    private ImageView nextBtn;
    private GridView calendarGrid;
    private Context context;
//    private EventModel model;
    public static List<MovieEvent> selectedEvents;

    public CalendarView(Context context)
    {
        super(context);
        this.context = context;
        init(context);
    }

    public CalendarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
        //setGridCellClickEvents();
    }

    private void init(final Context context)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.calendar_view, this);

        // Start in the current month
        displayedMonth = Calendar.getInstance();

        monthHeader = (TextView) findViewById(R.id.calendar_month_display);
        prevBtn = (ImageView) findViewById(R.id.calendar_prev_button);
        nextBtn = (ImageView) findViewById(R.id.calendar_next_button);
        calendarGrid = (GridView) findViewById(R.id.calendar_grid);

        nextBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                displayedMonth.add(Calendar.MONTH, 1);
                updateCalendar(null);
            }
        });

        prevBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                displayedMonth.add(Calendar.MONTH, -1);
                updateCalendar(null);
            }
        });

//        calendarGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               // Toast.makeText(CalendarView.this,"Clicked ",Toast.LENGTH_LONG).show();
//                Calendar c = Calendar.getInstance();
//               int month = c.get(Calendar.MONTH);
//                month = month+1;
//
//                String selectedDate = position + "/" + month + "/" + c.get(Calendar.YEAR);
//                selectedEvents =  getAllEventsForSelectedDay(selectedDate);
//            }
//        });

        updateCalendar(null);
    }



//
//    public void updateCalendar()
//    {
//        if (model != null)
//        {
//            updateCalendar(model.getDaysInMonthWithEvents(
//                    displayedMonth.get(Calendar.MONTH),
//                    displayedMonth.get(Calendar.YEAR)));
//        }
//        else
//        {
//            updateCalendar(null);
//        }
//    }

    public void updateCalendar(Set<Integer> daysWithEvents)
    {
        List<Calendar> cells = new ArrayList<>();
        Calendar cal = (Calendar) displayedMonth.clone();
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int month = displayedMonth.get(Calendar.MONTH);
        int year = displayedMonth.get(Calendar.YEAR);

        // Determine cell for start of displayed month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int monthStartCell = cal.get(Calendar.DAY_OF_WEEK) - 1;

        // Move calendar back to the beginning of the week (in previous month)
        cal.add(Calendar.DAY_OF_MONTH, -monthStartCell);

        // Fill cell array with blanks for previous month's days
        while (cells.size() < monthStartCell)
        {
            cells.add((Calendar)cal.clone());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Fill cell array with current month's days
        for (int i = 0; i < daysInMonth; i++)
        {
            String DateVal = (i+1) + "/"+ month + "/" + year;
            cells.add((Calendar)cal.clone());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        calendarGrid.setAdapter(new CalendarAdapter(
                getContext(), cells, month, year, daysWithEvents));

        monthHeader.setText(MONTH_FORMAT.format(displayedMonth.getTime()));
    }

    public CalendarAdapter getCalendarAdapter()
    {
        return (CalendarAdapter) calendarGrid.getAdapter();
    }
}
