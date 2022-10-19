package com.example.coursework;

import java.io.Serializable;

public class TripModel implements Serializable {

    private String tripName, tripDestination,tripDate,tripAssessment,tripDescription;
    private int tripId;

    // creating constructor for our variables.
    public int getId() {
        return tripId;
    }
    // creating getter and setter methods.
    public String getTripName() {

        return tripName;
    }
    // creating getter and setter methods.
    public String getTripDestination() {

        return tripDestination;
    }
    public String getTripDate() {

        return tripDate;
    }
    public String getTripAssessment() {

        return tripAssessment;
    }
    public String getTripDescription() {

        return tripDescription;
    }

    public void setId(int tripId) {
        this.tripId = tripId;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }
    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }
    public void setTripAssessment(String tripAssessment) {
        this.tripAssessment = tripAssessment;
    }
    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

}
