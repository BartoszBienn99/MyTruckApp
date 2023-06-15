package com.MyTruckApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numberOfKilometers;
    private String placeOfDeparture;
    private String placeOfDestination;

    public Track(int id, String numberOfKilometers, String placeOfDeparture, String placeOfDestination) {
        this.id = id;
        this.numberOfKilometers = numberOfKilometers;
        this.placeOfDeparture = placeOfDeparture;
        this.placeOfDestination = placeOfDestination;
    }

    public Track() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberOfKilometers() {
        return numberOfKilometers;
    }

    public void setNumberOfKilometers(String numberOfKilometers) {
        this.numberOfKilometers = numberOfKilometers;
    }

    public String getPlaceOfDeparture() {
        return placeOfDeparture;
    }

    public void setPlaceOfDeparture(String placeOfDeparture) {
        this.placeOfDeparture = placeOfDeparture;
    }

    public String getPlaceOfDestination() {
        return placeOfDestination;
    }

    public void setPlaceOfDestination(String placeOfDestination) {
        this.placeOfDestination = placeOfDestination;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", numberOfKilometers='" + numberOfKilometers + '\'' +
                ", placeOfDeparture='" + placeOfDeparture + '\'' +
                ", placeOfDestination='" + placeOfDestination + '\'' +
                '}';
    }
}
