package com.movieplanner.Handler;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.movieplanner.Model.Movie;
import com.movieplanner.Model.MovieEvent;
import com.movieplanner.R;
import com.movieplanner.View.ListViewFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public MovieEvent newEvent;

    public List<Movie> parseMoviesFile(Context context) {
        // resource reference to events.txt in res/raw/ folder of your project

        List<Movie> movies = new ArrayList<>();
        try (Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.movies))) {
            // match comma and 0 or more whitespace OR trailing space and newline

            scanner.useDelimiter(",\\s*|\\s*\\n+");
            //loop through the lines and get all instance values
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] splitText = line.split(",");

                    // Removing double quotes
                String id = splitText[0].replaceAll("^\"|\"$", "");
                String title = splitText[1].replaceAll("^\"|\"$", "");
                String year = splitText[2].replaceAll("^\"|\"$", "");
                String poster = splitText[3].replaceAll("^\"|\"$", "");

                boolean add = movies.add(new Movie(id, title, year, poster));
            }
        } catch (Resources.NotFoundException e) {
        }
        return movies;
    }

    public List<MovieEvent> parseEventsFile(Context context) {
        // resource reference to events.txt in res/raw/ folder of your project

        List<MovieEvent> eventsList = new ArrayList<>();
        try (Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.events))) {
            // match comma and 0 or more whitespace OR trailing space and newline
            if(newEvent != null){
                eventsList.add(newEvent);
            }
            scanner.useDelimiter(",\\s*|\\s*\\n+");
            //loop through the lines and get all instance values
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] splitText = line.split(",");

                // Removing double quotes
                String id = splitText[0].replaceAll("^\"|\"$", "");
                String title = splitText[1].replaceAll("^\"|\"$", "");
                String startDate = splitText[2].replaceAll("^\"|\"$", "");
                String endDate = splitText[3].replaceAll("^\"|\"$", "");
                String venue = splitText[4].replaceAll("^\"|\"$", "");
                String location = splitText[5].replaceAll("^\"|\"$", "");

                eventsList.add(new MovieEvent(id, title,  startDate, endDate,venue, location));

                // add lists to AllEvents arraylist after object creation

                ListViewFragment listViewFragment = new ListViewFragment();
                listViewFragment.AllEvents.add(new MovieEvent(id, title,  startDate, endDate, venue, location));

            }
           // Log.i("value","ooo - "+eventsList.size());
        } catch (Resources.NotFoundException e) {
        }

        return eventsList;

    }

}
