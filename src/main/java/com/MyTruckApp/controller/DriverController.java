package com.MyTruckApp.controller;

import com.MyTruckApp.model.Driver;
import com.MyTruckApp.service.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drivers")
public class DriverController {

@Autowired
private DriverServiceImpl driverService;

@GetMapping
    public List<Driver> getAllDrivers() {
    return driverService.getAllDrivers();
}

@GetMapping("/{id}")
    public Optional<Driver> getDriverById(@PathVariable int id) {
    Optional<Driver> response = driverService.getDriverById(id);
    return response;

}


}


