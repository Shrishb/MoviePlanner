package com.movieplanner.Controller.Listener;

import com.movieplanner.Model.Attendees;
import com.movieplanner.Model.Movie;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Miscelleneaous {

    // Static Attendees list
    public static List<Attendees> AllAttendees = new ArrayList<>();

    // Method for getting movie object from string
    public static Movie findMovieObjByID(List<Movie> list, String MovieName){

        Movie singleMovieObj = new Movie();

        for(int i=0;i<list.size();i++){
            if(MovieName.equals(list.get(i).getTitle())){
                singleMovieObj = list.get(i);
            }
        }

        return singleMovieObj;
    }

    // method for contact separations
    public static String[] findContacts(String AttendeesVal){

        String[] contactsval = new String[10];
        contactsval = AttendeesVal.split(",");

        return contactsval;
    }

    public static Date convertStringToDate(String dateInString){
        DateFormat format = new SimpleDateFormat("d/MM/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // Attendees objects method

//    public static void Create_New_AttendeesObj(String AttendeesVal){
//
//        // passing to above method for seperations
//        String[] commasepVal = findContacts(AttendeesVal);
//
//        for(int i=0;i<commasepVal.length;i++){
//
//            for(int j=0;j<AllAttendees.size();j++){
//                // check if complete Attendees list
//                if(!commasepVal[i].equals(AllAttendees.get(j).getEmail())){
//                    AllAttendees.add(new Attendees(null, commasepVal[i]));
//
//                    // break loop after adding
//                    break;
//                }
//            }
//        }
//    }
}
