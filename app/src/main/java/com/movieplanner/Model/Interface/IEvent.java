package com.movieplanner.Model.Interface;

import java.util.List;

public interface IEvent {
     String getEventId();

     String getEventTitle();

     String getStartDate();

     String getEndDate();

     String getVenue() ;

     String getLocation();

     List<String> getContacts();
}
