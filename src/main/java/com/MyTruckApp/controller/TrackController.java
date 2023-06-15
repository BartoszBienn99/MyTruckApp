package com.MyTruckApp.controller;

import com.MyTruckApp.model.Track;
import com.MyTruckApp.service.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackServiceImpl trackService;

    @GetMapping
    public List<Track> getAllTracks(){
        return trackService.getAllTracks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable int id){
        Optional<Track> response = trackService.getTrackById(id);
        return response
                .map(trackResponse -> ResponseEntity.ok(trackResponse))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Track> addNewTrack(@RequestBody Track requestTrack){
        Track savedTrack = trackService.addTrack(requestTrack);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTrack);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeTrackById(@PathVariable int id) {
        if(trackService.getTrackById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            trackService.removeById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
