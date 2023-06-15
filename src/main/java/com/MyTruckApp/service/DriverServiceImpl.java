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
    public void removeById(int id) {
        driverRepository.deleteById(id);

    }

    @Override
    public void setIsFree(boolean isFree, Integer id) {
        Optional<Driver> optional = driverRepository.findById(id);
        if(optional.isPresent()){
           Driver driver = optional.get();
            driver.setFree(isFree);
            driverRepository.save(driver);
        } else {
            throw new RuntimeException("Nie znaleziono kierowcy o id: " + id);
        }

    }


    @Override
    public Optional<Driver> getDriverById(int id) {
        Optional<Driver> optional = driverRepository.findById(id);
        if(optional.isEmpty())
        {
            throw new RuntimeException("Nie znaleziono kierowcy o id: " + id);
        }
        return optional;
    }
}
