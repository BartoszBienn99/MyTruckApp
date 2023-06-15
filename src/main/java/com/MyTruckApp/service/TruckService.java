package com.MyTruckApp.service;
import com.MyTruckApp.model.Truck;
import java.util.List;
import java.util.Optional;

public interface TruckService {

    List<Truck> getAllTrucks();

    Truck addTruck(Truck truck);


    void removeById(int id);

    Optional<Truck> getTruckById(int id);
}
