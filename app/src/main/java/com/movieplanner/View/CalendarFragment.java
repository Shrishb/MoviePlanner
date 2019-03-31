package com.movieplanner.View;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.movieplanner.R;

import java.util.Calendar;


public class CalendarFragment extends Fragment
{
    private ActionBarDrawerToggle drawerToggle;
    private CalendarView calendarView;
    private GridView calendarGrid;
    private ListView eventList;
    private Calendar selectedDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.activity_calendar, container, false);

        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        setUpFloatingActionButton();
        calendarView = (CalendarView) getView().findViewById(R.id.calendar);
        calendarGrid = (GridView) getView().findViewById(R.id.calendar_grid);
        eventList = (ListView) getView().findViewById(R.id.calendar_event_list);

        eventList.setEmptyView(getView().findViewById(R.id.empty_list_text));
    }

    private void setUpFloatingActionButton()
    {
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent addEventIntent = new Intent(getActivity(), NewEvent.class);
                startActivity(addEventIntent);
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if ((requestCode == IntentRequestCodes.VIEW_EVENT_REQUEST)
//                || (requestCode == IntentRequestCodes.ADD_EVENT_REQUEST))
//        {
//            if (resultCode == RESULT_OK)
//            {
//                if (data.getBooleanExtra("updateList", false))
//                {
//                    if (selectedDate != null
//                            && eventListAdapter != null)
//                    {
//                        eventListAdapter.clear();
//                        eventListAdapter.addAll(model.getEventsOnDay(
//                                selectedDate.get(Calendar.DAY_OF_MONTH),
//                                selectedDate.get(Calendar.MONTH),
//                                selectedDate.get(Calendar.YEAR)));
//                        eventListAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//        }
//    }
}
