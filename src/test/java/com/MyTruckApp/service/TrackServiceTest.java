package com.MyTruckApp.service;

import com.MyTruckApp.model.Track;
import com.MyTruckApp.repository.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackServiceImpl trackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTracks_ReturnsListOfTracks() {
        List<Track> tracks = new ArrayList<>();
        Track track1 = new Track();
        track1.setId(1);
        tracks.add(track1);

        Track track2 = new Track();
        track2.setId(2);
        tracks.add(track2);

        when(trackRepository.findAll()).thenReturn(tracks);

        List<Track> result = trackService.getAllTracks();

        assertEquals(tracks, result);
        verify(trackRepository, times(1)).findAll();
    }

    @Test
    void addTrack_ReturnsAddedTrack() {
        Track track = new Track();
        track.setId(1);

        when(trackRepository.save(any(Track.class))).thenReturn(track);

        Track result = trackService.addTrack(track);

        assertEquals(track, result);
        verify(trackRepository, times(1)).save(track);
    }

    @Test
    void removeById_ValidId_RemovesTrack() {
        int id = 1;
        Track track = new Track();
        track.setId(id);

        when(trackRepository.findById(id)).thenReturn(Optional.of(track));

        trackService.removeById(id);

        verify(trackRepository, times(1)).findById(id);
        verify(trackRepository, times(1)).save(track);
        verify(trackRepository, times(1)).deleteById(id);
    }


    @Test
    void getTrackById_ValidId_ReturnsOptionalTrack() {
        int id = 1;
        Track track = new Track();
        track.setId(id);

        when(trackRepository.findById(id)).thenReturn(Optional.of(track));

        Optional<Track> result = trackService.getTrackById(id);

        assertEquals(Optional.of(track), result);
        verify(trackRepository, times(1)).findById(id);
    }

    @Test
    void getTrackById_InvalidId_ThrowsRuntimeException() {
        int id = 1;

        when(trackRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> trackService.getTrackById(id));

        verify(trackRepository, times(1)).findById(id);
    }
}
