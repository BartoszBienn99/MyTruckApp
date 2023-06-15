package com.MyTruckApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nameOfTruck;
    private String typeOfTruck;
    private String truckBrand;

    public Truck(int id, String nameOfTruck, String typeOfTruck, String truckBrand) {
        this.id = id;
        this.nameOfTruck = nameOfTruck;
        this.typeOfTruck = typeOfTruck;
        this.truckBrand = truckBrand;
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
