package com.MyTruckApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numberOfKilometers;
    private String placeOfDeparture;
    private String placeOfDestination;

    @ManyToMany(mappedBy = "tracks", cascade = CascadeType.ALL)
    private List<Truck> trucks;

    public Track(int id, String numberOfKilometers, String placeOfDeparture, String placeOfDestination, List<Truck> trucks) {
        this.id = id;
        this.numberOfKilometers = numberOfKilometers;
        this.placeOfDeparture = placeOfDeparture;
        this.placeOfDestination = placeOfDestination;
        this.trucks = trucks;
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

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
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
