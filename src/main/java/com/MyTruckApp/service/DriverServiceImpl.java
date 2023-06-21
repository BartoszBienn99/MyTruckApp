package com.MyTruckApp.service;



import com.MyTruckApp.dto.UpdateDriverDto;
import com.MyTruckApp.model.Company;
import com.MyTruckApp.model.Driver;
import com.MyTruckApp.model.Truck;
import com.MyTruckApp.repository.CompanyRepository;
import com.MyTruckApp.repository.DriverRepository;
import com.MyTruckApp.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private CompanyRepository companyRepository;


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
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if(driverOptional.isPresent()){
            Driver driver = driverOptional.get();
            Truck truck = driver.getTruck();
            if(truck != null) {
                driver.setTruck(null);
            }
            Company company = driver.getCompany();
            if(company != null) {
                driver.setCompany(null);
            }
            driverRepository.deleteById(id);
        }
    }

    @Override
    public void setIsFree(boolean isFree, Integer id) {
        Optional<Driver> optional = driverRepository.findById(id);
        if (optional.isPresent()) {
            Driver driver = optional.get();
            driver.setFree(isFree);
            driverRepository.save(driver);
        } else {
            throw new RuntimeException("Nie znaleziono kierowcy o id: " + id);
        }

    }

    @Override
    public void updateDriver(Integer driverId, UpdateDriverDto updateDriverDto) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        Optional<Truck> optionalTruck =  truckRepository.findById(updateDriverDto.getTruckId());
        Optional<Company> optionalCompany = companyRepository.findById(updateDriverDto.getCompanyId());
        if (optionalDriver.isPresent() || optionalTruck.isPresent() || optionalCompany.isPresent()) {
            Driver existingDriver = optionalDriver.get();
            Truck existingTruck = optionalTruck.get();
            Company existingCompany = optionalCompany.get();
            existingDriver.setTruck(existingTruck);
            existingDriver.setCompany(existingCompany);
            driverRepository.save(existingDriver);
        } else {
            throw new RuntimeException("Nie znaleziono Kierowcy/Ciężarówki/Firmy");
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
