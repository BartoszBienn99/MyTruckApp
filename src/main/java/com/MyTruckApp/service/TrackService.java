package com.MyTruckApp.service;

import com.MyTruckApp.model.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    List<Track> getAllTracks();

    Track addTrack(Track track);

    void removeById(int id);

    Optional<Track> getTrackById(int id);
}
