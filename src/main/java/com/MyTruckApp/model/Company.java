package com.MyTruckApp.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String companyName;
    private String productName;
    private String country;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Driver> drivers;

    public Company(int id, String companyName, String productName, String country, List<Driver> drivers) {
        this.id = id;
        this.companyName = companyName;
        this.productName = productName;
        this.country = country;
        this.drivers = drivers;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", productName='" + productName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
