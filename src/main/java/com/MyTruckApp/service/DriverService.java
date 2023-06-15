package com.MyTruckApp.service;

import com.MyTruckApp.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> getAllDrivers();

    Driver getDriverById(int id);
}
