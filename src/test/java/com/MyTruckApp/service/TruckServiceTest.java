package com.MyTruckApp.service;

import com.MyTruckApp.model.Driver;
import com.MyTruckApp.model.Track;
import com.MyTruckApp.model.Truck;
import com.MyTruckApp.repository.DriverRepository;
import com.MyTruckApp.repository.TruckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TruckServiceTest {

    @Mock
    private TruckRepository truckRepository;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private TruckServiceImpl truckService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfTrucksWhenGetAllTrucksCalled() {
        // Given
        List<Truck> expectedTrucks = new ArrayList<>();
        expectedTrucks.add(new Truck());
        expectedTrucks.add(new Truck());

        when(truckRepository.findAll()).thenReturn(expectedTrucks);

        // When
        List<Truck> actualTrucks = truckService.getAllTrucks();

        // Then
        assertEquals(expectedTrucks, actualTrucks);
        verify(truckRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnAddedTruckWhenAddTruckCalled() {
        // Given
        Truck truck = new Truck();
        when(truckRepository.save(truck)).thenReturn(truck);

        // When
        Truck savedTruck = truckService.addTruck(truck);

        // Then
        assertEquals(truck, savedTruck);
        verify(truckRepository, times(1)).save(truck);
    }

    @Test
    void shouldRemoveTruckWhenRemoveByIdCalled() {
        // Given
        int truckId = 123;
        Truck truck = new Truck();
        Driver driver = new Driver();
        driver.setId(0);
        driver.setDriverFirstName("John");
        driver.setDriverLastName("Doe");
        driver.setFree(false);
        truck.setDriver(driver);

        when(truckRepository.findById(truckId)).thenReturn(Optional.of(truck));

        // When
        truckService.removeById(truckId);

        // Then
        verify(driverRepository, times(1)).save(driver);
        verify(truckRepository, times(1)).save(truck);
        verify(truckRepository, times(1)).deleteById(truckId);
    }

    @Test
    void shouldReturnOptionalTruckWhenGetTruckByIdCalled() {
        // Given
        int truckId = 1;
        Truck truck = new Truck();
        truck.setId(truckId);
        Optional<Truck> optionalTruck = Optional.of(truck);

        when(truckRepository.findById(truckId)).thenReturn(optionalTruck);

        // When
        Optional<Truck> actualTruck = truckService.getTruckById(truckId);

        // Then
        assertEquals(optionalTruck, actualTruck);
        verify(truckRepository, times(1)).findById(truckId);
    }

    @Test
    void shouldAddTrackToTruckWhenAddTrackToTruckCalled() {
        // Given
        Truck truck = new Truck();
        Track track = new Track();

        // Mocking the behavior of the truck object and the truckRepository
        when(truckRepository.save(any(Truck.class))).thenAnswer(invocation -> invocation.getArgument(0));
        List<Track> tracks = new ArrayList<>();
        truck.setTracks(tracks);

        // When
        truckService.addTrackToTruck(truck, track);

        // Then
        assertTrue(tracks.contains(track));
        verify(truckRepository, times(1)).save(truck);
    }
}
