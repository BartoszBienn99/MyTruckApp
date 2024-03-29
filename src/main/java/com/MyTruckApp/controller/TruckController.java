package com.MyTruckApp.controller;

import com.MyTruckApp.dto.AddTrackToTruckDto;
import com.MyTruckApp.model.Track;
import com.MyTruckApp.model.Truck;
import com.MyTruckApp.repository.DriverRepository;
import com.MyTruckApp.repository.TruckRepository;
import com.MyTruckApp.service.TrackServiceImpl;
import com.MyTruckApp.service.TruckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckServiceImpl truckService;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private TrackServiceImpl trackService;


    @GetMapping
    public List<Truck> getAllTrucks() {
        return truckService.getAllTrucks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Truck> getTruckById(@PathVariable int id){
        Optional<Truck> response = truckService.getTruckById(id);
        return response
                .map(truckResponse -> ResponseEntity.ok(truckResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Truck> addNewTruck(@RequestBody Truck requestTruck){
        Truck savedTruck = truckService.addTruck(requestTruck);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTruck);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTruckById(@PathVariable int id) {
        if(truckService.getTruckById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            truckService.removeById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/addTrackToTruck/{id}")
    public ResponseEntity<Void> addTrackToTruck(@PathVariable int id, @RequestBody AddTrackToTruckDto addTrackToTruckDto){
      Optional<Track> optionalTrack = trackService.getTrackById(addTrackToTruckDto.getTrackId());
      Optional<Truck> optionalTruck = truckService.getTruckById(id);

      if(optionalTrack.isEmpty() || optionalTruck.isEmpty()){
          return ResponseEntity.notFound().build();
      }

      Truck truck = optionalTruck.get();
      Track track = optionalTrack.get();


      truckService.addTrackToTruck(truck, track);

      return ResponseEntity.noContent().build();

    }
}
