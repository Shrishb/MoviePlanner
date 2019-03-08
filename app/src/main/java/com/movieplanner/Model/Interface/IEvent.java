package com.movieplanner.Model.Interface;

import java.util.Date;
import java.util.List;

public interface IEvent {
     String getEventId();

     String getEventTitle();

     Date getStartDate();

     Date getEndDate();

     String getVenue() ;

     String getLocation();

     List<String> getContacts();
}
