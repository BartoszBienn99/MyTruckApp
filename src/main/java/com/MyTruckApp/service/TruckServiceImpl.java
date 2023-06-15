package com.MyTruckApp.service;
import com.MyTruckApp.model.Truck;
import com.MyTruckApp.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckServiceImpl implements TruckService {


    @Autowired
    private TruckRepository truckRepository;

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
}
