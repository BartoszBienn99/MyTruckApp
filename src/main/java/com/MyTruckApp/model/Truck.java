package com.MyTruckApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nameOfTruck;
    private String typeOfTruck;
    private String truckBrand;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "truck_track",
    joinColumns = @JoinColumn(name = "truck_id"),
    inverseJoinColumns = @JoinColumn(name = "track_id"))
    private List<Track> tracks;

    @OneToOne(mappedBy = "truck", cascade = CascadeType.ALL)
    private Driver driver;

    public Truck(int id, String nameOfTruck, String typeOfTruck, String truckBrand, List<Track> tracks, Driver driver) {
        this.id = id;
        this.nameOfTruck = nameOfTruck;
        this.typeOfTruck = typeOfTruck;
        this.truckBrand = truckBrand;
        this.tracks = tracks;
        this.driver = driver;
    }

    public Truck() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfTruck() {
        return nameOfTruck;
    }

    public void setNameOfTruck(String nameOfTruck) {
        this.nameOfTruck = nameOfTruck;
    }

    public String getTypeOfTruck() {
        return typeOfTruck;
    }

    public void setTypeOfTruck(String typeOfTruck) {
        this.typeOfTruck = typeOfTruck;
    }

    public String getTruckBrand() {
        return truckBrand;
    }

    public void setTruckBrand(String truckBrand) {
        this.truckBrand = truckBrand;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", nameOfTruck='" + nameOfTruck + '\'' +
                ", typeOfTruck='" + typeOfTruck + '\'' +
                ", truckBrand='" + truckBrand + '\'' +
                '}';
    }
}
