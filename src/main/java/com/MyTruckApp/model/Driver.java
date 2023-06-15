package com.MyTruckApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String driverFirstName;
    private String driverLastName;
    private boolean isFree;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    public Driver(int id, String driverFirstName, String driverLastName, boolean isFree) {
        this.id = id;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.isFree = isFree;
    }


    public Driver() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driverFirstName='" + driverFirstName + '\'' +
                ", driverLastName='" + driverLastName + '\'' +
                ", isFree=" + isFree +
                '}';
    }
}
