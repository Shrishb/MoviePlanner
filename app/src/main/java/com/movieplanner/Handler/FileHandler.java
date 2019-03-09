package com.movieplanner.Handler;

import android.content.Context;
import android.content.res.Resources;
import com.movieplanner.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public List<String> parseMoviesFile(Context context) {
        // resource reference to events.txt in res/raw/ folder of your project
        // supports trailing comments with //
        List<String> movies = new ArrayList<>();
        try (Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.events))) {
            // match comma and 0 or more whitespace OR trailing space and newline

            scanner.useDelimiter(",\\s*|\\s*\\n+");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] splitText = line.split(",");
                int id = Integer.parseInt(splitText[0]);

                String title = splitText[1];
                String year = splitText[2];
                String poster = splitText[3];
            }
        } catch (Resources.NotFoundException e) {
        }
        return movies;
    }

}
