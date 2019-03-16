package com.movieplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.movieplanner.Handler.FileHandler;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.View.NewEvent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private List<MovieEvent> list;

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        //initializing views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         list = new ArrayList<>();

        loadRecyclerViewItem();
    }

    //todo: replace the inner code with the filehandler code
    private void loadRecyclerViewItem() {
        //you can fetch the data from server or some apis
        //for this tutorial I am adding some dummy data directly
        FileHandler oo = new FileHandler();
        MovieEvent myList;
        List moviesData = oo.parseMoviesFile(context);
        for (int i = 0; i <  moviesData.size(); i++) {
             myList = new MovieEvent(
                     oo.parseMoviesFile(context).get(i).getId(), oo.parseMoviesFile(context).get(i).getTitle(),
                    oo.parseMoviesFile(context).get(i).getPoster()
            );


           Log.d("Hello","dddddd"+oo.parseMoviesFile(context).get(0).getTitle());

            list.add(myList);
        }

        adapter = new EventsAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    //call new intext to load new_event view
    public void onNewEventClick(View args){

        Intent newEventIntent = new Intent(MainActivity.this,
                NewEvent.class);

        startActivity(newEventIntent);
    }
}
