package com.MyTruckApp.service;
import com.MyTruckApp.model.Driver;
import com.MyTruckApp.model.Track;
import com.MyTruckApp.model.Truck;
import com.MyTruckApp.repository.DriverRepository;
import com.MyTruckApp.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckServiceImpl implements TruckService {


    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    @Override
    public Truck addTruck(Truck truck) {
        return truckRepository.save(truck);
    }


    @Override
    public void removeById(int id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if(optionalTruck.isPresent()) {
            Optional<Driver> optionalDriver = Optional.ofNullable(optionalTruck.get().getDriver());
            optionalDriver.ifPresent(driver -> {
                driver.setTruck(null);
                driverRepository.save(driver);
            });
        }
        truckRepository.deleteById(id);
    }

    @Override
    public Optional<Truck> getTruckById(int id) {
            Optional<Truck> optional = truckRepository.findById(id);
            if(optional.isEmpty())
            {
                throw new RuntimeException("Nie znaleziono ciężarówki o id: " + id);
            }
            return optional;
    }

    @Override
    public void addTrackToTruck(Truck truck, Track track) {
        List<Track> tracks = truck.getTracks();
        tracks.add(track);
        truck.setTracks(tracks);
        truckRepository.save(truck);
    }
}
