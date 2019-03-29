package com.movieplanner;

import android.content.Context;
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
import com.movieplanner.View.NewEvent;
import com.movieplanner.View.ViewMovies;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.activity_main, container, false);

        return v;
    }


    private Context context;
    private List<MovieEvent> list;

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_main);
        //context = this;

        //initializing views
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();

        Button button = (Button) getView().findViewById(R.id.addEvents);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newEventIntent = new Intent(getActivity(),
                        NewEvent.class);

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
                    fileHandler.parseEventsFile(getActivity()).get(i).getLocation(), fileHandler.parseEventsFile(getActivity()).get(i).getVenue()
            );

            list.add(myList);
        }

        adapter = new EventsAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
    }

    //call new intent to load new_event view
    public void onNewEventClick(View args){

        Intent newEventIntent = new Intent(getActivity(),
                NewEvent.class);

        startActivity(newEventIntent);
    }

    //call new intent to load movies_list view
    public void onViewMoviesClick(View args){

        Intent viewMoviesIntent = new Intent(getActivity(),
                ViewMovies.class);

        startActivity(viewMoviesIntent);
    }
}