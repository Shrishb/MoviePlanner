package com.movieplanner.Model;

import com.movieplanner.Model.Interface.IEvent;

import java.util.List;

public class MovieEvent implements IEvent {



    private String eventId;

    private String eventTitle;

    private String startDate;

    private String endDate;

    private String venue;

    private String location;

    private List<String> Contacts;

    public MovieEvent(String eventId, String eventTitle, String startDate,
                      String endDate, String venue, String location, List<String> contacts){
        this.eventId =eventId;
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.location = location;
        this.Contacts = contacts;
    }

    public MovieEvent(String eventId, String eventTitle,String startDate,
                      String endDate, String venue ,String location){
        this.eventId =eventId;
        this.eventTitle = eventTitle;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    // Getter and setters

    public String getEventId() {
        return eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
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



    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContacts(List<String> contacts) {
        Contacts = contacts;
    }
}
