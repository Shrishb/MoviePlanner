package com.movieplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.movieplanner.Adapter.EventsAdapter;
import com.movieplanner.Handler.FileHandler;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.View.NewEvent;
import com.movieplanner.View.ViewMovies;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private  List<MovieEvent> list;


    // List to staore all events
    public static List<MovieEvent> AllEvents = new ArrayList<>();

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Log.i("Start","Creating");

        //initializing views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


         list = new ArrayList<>();

            loadRecyclerViewItem();


    }



    private void loadRecyclerViewItem() {

      // call filehandler class method to generate events details in card layout
        FileHandler fileHandler = new FileHandler();
        MovieEvent myList;
        // Check if static arraylist has value
        if(AllEvents.isEmpty()){

           // load from file 1st time
            List<MovieEvent> eventsData = fileHandler.parseEventsFile(context);


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

            adapter = new EventsAdapter(list, this);
            recyclerView.setAdapter(adapter);
            //Log.i("listsss",AllEvents.toString());
        }

        else{
            // Load static lists
            Log.i("size",Integer.toString(AllEvents.size()));
            Log.i("lists",AllEvents.toString());
            adapter = new EventsAdapter(AllEvents, this);
            recyclerView.setAdapter(adapter);
        }

    }



    //call new intent to load new_event view
    public void onNewEventClick(View args){

        Intent newEventIntent = new Intent(MainActivity.this,
                NewEvent.class);

        startActivity(newEventIntent);
    }

    //call new intent to load movies_list view
    public void onViewMoviesClick(View args){

        Intent viewMoviesIntent = new Intent(MainActivity.this,
                ViewMovies.class);

        startActivity(viewMoviesIntent);
    }
}
