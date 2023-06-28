package com.MyTruckApp.controller;

import com.MyTruckApp.dto.UpdateDriverDto;
import com.MyTruckApp.model.Driver;
import com.MyTruckApp.service.DriverServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DriverControllerTest {

    @Mock
    private DriverServiceImpl driverService;

    @InjectMocks
    private DriverController driverController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnListOfDriversWhenGetAllDriversCalled() {
        // Given
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver());
        drivers.add(new Driver());

        when(driverService.getAllDrivers()).thenReturn(drivers);

        // When
        List<Driver> result = driverController.getAllDrivers();

        // Then
        assertEquals(drivers, result);
        verify(driverService, times(1)).getAllDrivers();
    }

    @Test
    void shouldReturnDriverWhenGetDriverByIdCalledWithExistingId() {
        // Given
        int driverId = 1;
        Driver driver = new Driver();
        driver.setId(driverId);

        when(driverService.getDriverById(driverId)).thenReturn(Optional.of(driver));

        // When
        ResponseEntity<Driver> response = driverController.getDriverById(driverId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(driver, response.getBody());
        verify(driverService, times(1)).getDriverById(driverId);
    }

    @Test
    void shouldReturnNotFoundWhenGetDriverByIdCalledWithNonExistingId() {
        // Given
        int driverId = 1;

        when(driverService.getDriverById(driverId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Driver> response = driverController.getDriverById(driverId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(driverService, times(1)).getDriverById(driverId);
    }

    @Test
    void shouldReturnCreatedDriverWhenAddNewDriverCalled() {
        // Given
        Driver requestDriver = new Driver();
        Driver savedDriver = new Driver();

        when(driverService.addDriver(requestDriver)).thenReturn(savedDriver);

        // When
        ResponseEntity<Driver> response = driverController.addNewDriver(requestDriver);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDriver, response.getBody());
        verify(driverService, times(1)).addDriver(requestDriver);
    }

    @Test
    void shouldReturnNoContentWhenRemoveDriverByIdCalledWithExistingId() {
        // Given
        int driverId = 1;

        when(driverService.getDriverById(driverId)).thenReturn(Optional.of(new Driver()));

        // When
        ResponseEntity<Void> response = driverController.removeDriverById(driverId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(driverService, times(1)).getDriverById(driverId);
        verify(driverService, times(1)).removeById(driverId);
    }

    @Test
    void shouldReturnNotFoundWhenRemoveDriverByIdCalledWithNonExistingId() {
        // Given
        int driverId = 1;

        when(driverService.getDriverById(driverId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Void> response = driverController.removeDriverById(driverId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(driverService, times(1)).getDriverById(driverId);
        verify(driverService, never()).removeById(driverId);
    }

    @Test
    void shouldReturnTrueWhenUpdateDriverCalledWithValidData() {
        // Given
        int driverId = 1;
        boolean isFree = true;

        // When
        ResponseEntity<Boolean> response = driverController.updateDriver(driverId, isFree);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(driverService, times(1)).setIsFree(isFree, driverId);
    }

    @Test
    void shouldReturnDriverIdWhenUpdateDriverInfoCalledWithValidData() {
        // Given
        int driverId = 1;
        UpdateDriverDto updateDriverDto = new UpdateDriverDto();

        // When
        ResponseEntity<Integer> response = driverController.updateDriverInfo(driverId, updateDriverDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(driverId, response.getBody());
        verify(driverService, times(1)).updateDriver(driverId, updateDriverDto);
    }
}