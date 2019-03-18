package com.movieplanner.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private List<MovieEvent> eventsList;
    private Context context;

    public class EventsViewHolder extends RecyclerView.ViewHolder {
    public TextView id, title, location, menuOption;

    //constructor
    //todo : need to add other properties as well
    public EventsViewHolder(View view) {
        super(view);
        id = (TextView) view.findViewById(R.id.id);
        title = (TextView) view.findViewById(R.id.title);
        location = (TextView) view.findViewById(R.id.location);
        menuOption = (TextView) view.findViewById(R.id.menuOptions);
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


    @Override
    public void onBindViewHolder(final EventsAdapter.EventsViewHolder holder, int position) {
        MovieEvent movieEvent = eventsList.get(position);
        holder.id.setText(movieEvent.getEventId());
        holder.title.setText(movieEvent.getEventTitle());
        holder.location.setText(movieEvent.getLocation());

        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //will show popup menu here
                PopupMenu popup = new PopupMenu(context, holder.menuOption);

                //inflating menu from xml resource
                popup.inflate(R.menu.events_cardview_options);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            // for delete option remove object from arraylist and update recyclerview
                            case R.id.eventsDeleteOption:
                                eventsList.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                notifyItemRangeChanged(holder.getAdapterPosition(), eventsList.size());
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}

