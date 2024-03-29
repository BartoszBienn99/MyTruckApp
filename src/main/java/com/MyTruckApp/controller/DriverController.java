package com.MyTruckApp.controller;

import com.MyTruckApp.dto.UpdateDriverDto;
import com.MyTruckApp.model.Driver;
import com.MyTruckApp.service.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Driver> getDriverById(@PathVariable int id) {
        Optional<Driver> response = driverService.getDriverById(id);
        return response
                .map(driverResponse -> ResponseEntity.ok(driverResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Driver> addNewDriver(@RequestBody Driver requestDriver) {
        Driver savedDriver = driverService.addDriver(requestDriver);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDriver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDriverById(@PathVariable int id) {
        if (driverService.getDriverById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            driverService.removeById(id);
            return ResponseEntity.noContent().build();
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updateDriver(@PathVariable int id, @RequestBody boolean isFree) {
        driverService.setIsFree(isFree, id);
        return ResponseEntity.ok(isFree);


    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Integer> updateDriverInfo(@PathVariable int id, @RequestBody UpdateDriverDto updateDriverDto) {
        driverService.updateDriver(id, updateDriverDto);
        return ResponseEntity.ok(id);
    }

}



