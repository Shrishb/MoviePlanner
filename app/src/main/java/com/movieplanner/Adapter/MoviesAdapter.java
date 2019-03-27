package com.movieplanner.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.movieplanner.Model.Movie;
import com.movieplanner.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{
    private List<Movie> moviesList;
    private Context context;

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        //declare view fields
        public TextView title, year, menuOption;
        public ImageView poster;

        //constructor
        public MoviesViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.movieTitle);
            year = (TextView) view.findViewById(R.id.movieYear);
            poster = (ImageView) view.findViewById(R.id.moviePoster);
            menuOption = (TextView) view.findViewById(R.id.menuOptions);
        }
    }

    // declare MoviesAdapter and initialize values
    public MoviesAdapter(List<Movie> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    // populate movies list view and bind to parent
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list, parent, false);

        return new MoviesAdapter.MoviesViewHolder(itemView);
    }

    //todo: set image view from poster name

    //bind options ellipsis menu to each cardview item
    @Override
    public void onBindViewHolder(final MoviesAdapter.MoviesViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.poster.setImageResource(R.drawable.poster);

        // click handler to perform appropriate action based on option menu click
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //will show popup menu here
                PopupMenu popup = new PopupMenu(context, holder.menuOption);

                //inflating menu from xml resource
                popup.inflate(R.menu.movies_cardview_options);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            // for delete option remove object from arraylist and update recyclerview
                            case R.id.deleteMovieOption:
                                moviesList.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                notifyItemRangeChanged(holder.getAdapterPosition(), moviesList.size());
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
        return moviesList.size();
    }
}
