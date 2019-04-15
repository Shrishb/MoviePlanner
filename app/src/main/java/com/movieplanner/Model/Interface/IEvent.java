package com.movieplanner.Model.Interface;

import com.movieplanner.Model.Attendees;

import java.util.List;

public interface IEvent {
     String getEventId();

     String getEventTitle();

     String getStartDate();

     String getEndDate();

     String getVenue() ;

     String getLocation();

     List<Attendees> getContacts();
}
