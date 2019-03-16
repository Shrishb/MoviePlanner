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

                    // Removing double quotes
                String id = splitText[0].replaceAll("^\"|\"$", "");
                String title = splitText[1].replaceAll("^\"|\"$", "");
                String year = splitText[2].replaceAll("^\"|\"$", "");
                String poster = splitText[3].replaceAll("^\"|\"$", "");

                movies.add(new Movie(id, title, year, poster));
            }
        } catch (Resources.NotFoundException e) {
        }
        return movies;
    }

//    public List<Movie> getData(){
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        List<Movie> movies = new ArrayList<>();
//        try {
//            Log.d("Pathd", String.valueOf(getClass().getClassLoader().getResources("raw")));
//            BufferedReader br = new BufferedReader(new FileReader());
//            String line = null;
//                while ( br.readLine() != null) {
//                    String[] tmpData = line.split(",");
//                    Log.d("hggh",tmpData[0]);
//                    movies.add(new Movie(tmpData[0], tmpData[1],Integer.parseInt(tmpData[2]), tmpData[3]));
//                }
//            }
//        catch (FileNotFoundException e) {
//           Log.d("err","FileNotFound");
//        }
//        catch (IOException e) {
//            Log.d("err","other error");
//        }
//
//        return movies;
//    }
}
