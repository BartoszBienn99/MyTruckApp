package com.MyTruckApp.controller;

import com.MyTruckApp.model.Driver;
import com.MyTruckApp.service.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

@Autowired
private DriverServiceImpl driverService;

@GetMapping
    public List<Driver> getAllDrivers() {
    return driverService.getAllDrivers();
}


}


