package com.movieplanner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;
import com.movieplanner.View.EditEvent;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private List<MovieEvent> eventsList;
    private Context context;

    public class EventsViewHolder extends RecyclerView.ViewHolder {
    public TextView id, title, attendeesCount,startDate, endDate, MovieName;
    ImageView deleteEventBtn;

    //constructor
    //todo : need to add other properties as well
    public EventsViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.title);
        startDate = (TextView) view.findViewById(R.id.startDate);
        endDate = (TextView) view.findViewById(R.id.endDate);
        MovieName = (TextView) view.findViewById(R.id.MovieName);
        //attendeesCount = (TextView) view.findViewById(R.id.attendeesCount);
        deleteEventBtn = (ImageView) view.findViewById(R.id.event_delete_button);
        }
    }

    public EventsAdapter(List<MovieEvent> eventsList, Context context) {
        this.eventsList = eventsList;
        this.context = context;
    }

    // populate events list view and bind to parent
    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list, parent, false);

        return new EventsViewHolder(itemView);
    }

    //bind  events_cardview_options to show the menu options in each cardview
    @Override
    public void onBindViewHolder(final EventsAdapter.EventsViewHolder holder, int position) {
        MovieEvent movieEvent = eventsList.get(position);
        holder.title.setText(movieEvent.getEventTitle());
        //holder.attendeesCount.setText(movieEvent.getContacts());
        holder.startDate.setText(movieEvent.getStartDate());
        holder.endDate.setText(movieEvent.getEndDate());
        if(movieEvent.getMoviedetails() != null){
            holder.MovieName.setText(movieEvent.getMoviedetails().getTitle());
        }


        holder.deleteEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventsList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), eventsList.size());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditEventDetails(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    // open new editevent view to pass event details for editing
    private void onEditEventDetails(final EventsAdapter.EventsViewHolder holder){
        Intent MainIntent;
        MainIntent = new Intent(context,
                EditEvent.class);

        MainIntent.putExtra("eID", eventsList.get(holder.getAdapterPosition()).getEventId());
        MainIntent.putExtra("eTitle", eventsList.get(holder.getAdapterPosition()).getEventTitle());
        MainIntent.putExtra("eStartDate", eventsList.get(holder.getAdapterPosition()).getStartDate());
        MainIntent.putExtra("eEndDate", eventsList.get(holder.getAdapterPosition()).getEndDate());
        MainIntent.putExtra("eLocation", eventsList.get(holder.getAdapterPosition()).getLocation());
        MainIntent.putExtra("eVenue", eventsList.get(holder.getAdapterPosition()).getVenue());
        if(eventsList.get(holder.getAdapterPosition()).getMoviedetails() != null){
            MainIntent.putExtra("mTitle", eventsList.get(holder.getAdapterPosition()).getMoviedetails().getTitle());
        }
        else{
            MainIntent.putExtra("mTitle", "");
        }


        context.startActivity(MainIntent);
    }
}

