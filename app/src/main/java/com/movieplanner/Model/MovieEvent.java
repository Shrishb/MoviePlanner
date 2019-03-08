package com.movieplanner.Model;

import com.movieplanner.Model.Interface.IEvent;
import com.movieplanner.Model.Interface.IMovie;

import java.util.Date;
import java.util.List;

public abstract class MovieEvent implements IEvent {

    private String eventId;

    private String eventTitle;

    private Date startDate;

    private Date endDate;

    private String venue;

    private String location;

    private List<String> Contacts;

    public MovieEvent(String eventId, String eventTitle, Date startDate,
                      Date endDate, String venue, String location, List<String> contacts){
        this.eventId =eventId;
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.location = location;
        this.Contacts = contacts;
    }


    public String getEventId() {
        return eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getVenue() {
        return venue;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getContacts() {
        return Contacts;
    }
}
