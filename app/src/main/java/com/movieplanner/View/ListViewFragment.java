package com.movieplanner.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.movieplanner.Adapter.EventsAdapter;
import com.movieplanner.Handler.FileHandler;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment {

    private List<MovieEvent> list;

    // List to stare all events
    public static List<MovieEvent> AllEvents = new ArrayList<>();

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    //load the xml to be shown in the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
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
                Intent newEventIntent = new Intent(getActivity(), NewEvent.class);
                startActivity(newEventIntent);
            }
        });

        Button showMovies = (Button) getView().findViewById(R.id.viewMovies);
        showMovies.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newEventIntent = new Intent(getActivity(), ViewMovies.class);
                startActivity(newEventIntent);
            }
        });

        loadRecyclerViewItem();
    }

    private void loadRecyclerViewItem() {
        // call filehandler class method to generate events details in card layout
        FileHandler fileHandler = new FileHandler();
        MovieEvent myList;
        // Check if static arraylist has value
        if(AllEvents.isEmpty()){

            // load from file 1st time
            List<MovieEvent> eventsData = fileHandler.parseEventsFile(getActivity());


            for (int i = 0; i <  eventsData.size(); i++) {
                myList = new MovieEvent(
                        eventsData.get(i).getEventId(),
                        eventsData.get(i).getEventTitle(),
                        eventsData.get(i).getVenue(),
                        eventsData.get(i).getStartDate(),
                        eventsData.get(i).getEndDate(),
                        eventsData.get(i).getLocation()
                );

                list.add(myList);
            }

            adapter = new EventsAdapter(list, getActivity());
            recyclerView.setAdapter(adapter);
        }

        else{
            // Load static lists
            Log.i("size",Integer.toString(AllEvents.size()));
            Log.i("lists",AllEvents.toString());
            adapter = new EventsAdapter(AllEvents, getActivity());
            recyclerView.setAdapter(adapter);
        }
    }
}