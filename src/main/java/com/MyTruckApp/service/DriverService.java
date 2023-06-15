package com.MyTruckApp.service;

import com.MyTruckApp.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<Driver> getAllDrivers();

    Driver addDriver(Driver driver);

    void removeById(int id);

    void setIsFree(boolean isFree, Integer id);

   Optional<Driver> getDriverById(int id);
}
