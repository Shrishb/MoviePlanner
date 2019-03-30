package com.movieplanner.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    //load the xml to be shown in the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.activity_main, container, false);

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
        List eventsData = fileHandler.parseEventsFile(getActivity());

        for (int i = 0; i <  eventsData.size(); i++) {
            myList = new MovieEvent(
                    fileHandler.parseEventsFile(getActivity()).get(i).getEventId(), fileHandler.parseEventsFile(getActivity()).get(i).getEventTitle(),
                    fileHandler.parseEventsFile(getActivity()).get(i).getStartDate(),  fileHandler.parseEventsFile(getActivity()).get(i).getEndDate(),
                    fileHandler.parseEventsFile(getActivity()).get(i).getLocation(), fileHandler.parseEventsFile(getActivity()).get(i).getVenue()
            );

            list.add(myList);
        }

        adapter = new EventsAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
    }
}