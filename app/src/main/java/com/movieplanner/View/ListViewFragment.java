package com.movieplanner.View;

import android.content.Intent;
import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.movieplanner.Adapter.EventsAdapter;
import com.movieplanner.Handler.FileHandler;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListViewFragment extends Fragment {

    private List<MovieEvent> list;

    // List to stare all events
    public static List<MovieEvent> AllEvents = new ArrayList<>();
    FileHandler fileHandler = new FileHandler();
    List<MovieEvent> eventsData = fileHandler.parseEventsFile(getActivity());

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    //load the xml to be shown in the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.listview_fragment, container, false);

        return v;
    }

    // initialize  recyclerview of listview after xml is loaded
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //initializing views
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();

        Button callNewEvent = (Button) getView().findViewById(R.id.addEvents);
        callNewEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newEventIntent = new Intent(getActivity(), AddNewEvent.class);
                startActivity(newEventIntent);
            }
        });

        final ImageView sortEvents = (ImageView) getView().findViewById(R.id.sortEvents);
        sortEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //will show popup menu here
                PopupMenu popup = new PopupMenu(getActivity(), sortEvents);

                //inflating menu from xml resource
                popup.inflate(R.menu.actions_event_list);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.event_list_sort_asc:
                                sortEventsAscending();
                                adapter.notifyDataSetChanged();
                                item.setChecked(true);
                                return true;
                            case R.id.event_list_sort_desc:
                                //sortEventsDescending();
                                item.setChecked(true);
                                return true;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });

        loadRecyclerViewItem();
    }

    private void loadRecyclerViewItem() {
        // call filehandler class method to generate events details in card layout
        MovieEvent myList;
        // Check if static arraylist has value
        if(AllEvents.isEmpty()){

            // load from file 1st time

            for (int i = 0; i <  eventsData.size(); i++) {
                myList = new MovieEvent(
                        eventsData.get(i).getEventId(),
                        eventsData.get(i).getEventTitle(),
                        eventsData.get(i).getVenue(),
                        eventsData.get(i).getStartDate(),
                        eventsData.get(i).getEndDate(),
                        eventsData.get(i).getLocation(),
                        eventsData.get(i).getContacts()
                );

                list.add(myList);
            }

            adapter = new EventsAdapter(list, getActivity());
            recyclerView.setAdapter(adapter);
        }

        else{
            // Load static lists
            adapter = new EventsAdapter(AllEvents, getActivity());
            recyclerView.setAdapter(adapter);
        }
    }

    public void sortEventsAscending()
    {
        Collections.sort(eventsData, new Comparator<MovieEvent>()
        {
            @Override
            public int compare(MovieEvent o1, MovieEvent o2) {
                if (o1.getStartDate() == null || o2.getStartDate() == null)
                    return 0;
                return o1.getStartDate().compareTo(o2.getStartDate());
            }
        });
    }
}