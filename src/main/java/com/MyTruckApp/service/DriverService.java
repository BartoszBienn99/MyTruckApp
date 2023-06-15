package com.MyTruckApp.service;

import com.MyTruckApp.model.Driver;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<Driver> getAllDrivers();

    public Driver addDriver(Driver driver);

   Optional<Driver> getDriverById(int id);
}
