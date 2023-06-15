package com.MyTruckApp.service;

import com.MyTruckApp.model.Driver;
import com.MyTruckApp.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Optional<Driver> getDriverById(int id) {
        Optional<Driver> optional = driverRepository.findById(id);
        Driver driver = null;
        if(optional.isPresent()){
            driver = optional.get();
        } else {
            throw new RuntimeException("Nie znaleziono kierowcy o id: " + id);
        }
        return driverRepository.findById(id);
    }
}
