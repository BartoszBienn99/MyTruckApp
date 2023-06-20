package com.MyTruckApp.service;

import com.MyTruckApp.dto.UpdateDriverDto;
import com.MyTruckApp.model.Company;
import com.MyTruckApp.model.Driver;
import com.MyTruckApp.model.Truck;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<Driver> getAllDrivers();

    Driver addDriver(Driver driver);

    void removeById(int id);

    void setIsFree(boolean isFree, Integer id);

    void updateDriver(Integer driverId, UpdateDriverDto updateDriverDto);

   Optional<Driver> getDriverById(int id);
}
