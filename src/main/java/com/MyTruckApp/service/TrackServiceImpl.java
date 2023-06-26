package com.MyTruckApp.service;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.MyTruckApp.model.Track;
import com.MyTruckApp.model.Truck;
import com.MyTruckApp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track addTrack(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public void removeById(int id) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        if (optionalTrack.isPresent()) {
            optionalTrack.ifPresent(track -> {
                track.setTrucks(null);
                trackRepository.save(track);
            });
            trackRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Track> getTrackById(int id) {
        Optional<Track> optional = trackRepository.findById(id);
        if(optional.isEmpty())
        {
            throw new RuntimeException("Nie znaleziono trasy o id: " + id);
        }
        return optional;
    }
}
