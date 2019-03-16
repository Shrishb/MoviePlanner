package com.movieplanner.Handler;

import android.content.Context;
import android.content.res.Resources;

import com.movieplanner.Model.Movie;
import com.movieplanner.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    //todo fix extra double quotes on .split issue
    public List<Movie> parseMoviesFile(Context context) {
        // resource reference to events.txt in res/raw/ folder of your project

        List<Movie> movies = new ArrayList<>();
        try (Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.events))) {
            // match comma and 0 or more whitespace OR trailing space and newline

            scanner.useDelimiter(",\\s*|\\s*\\n+");
            //loop through the lines and get all instance values
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] splitText = line.split(",");

                String id = splitText[0];
                String title = splitText[1];
                int year = Integer.parseInt(splitText[2]);
                String poster = splitText[3];

                movies.add(new Movie(id, title, year, poster));
            }
        } catch (Resources.NotFoundException e) {
        }
        return movies;
    }

}
