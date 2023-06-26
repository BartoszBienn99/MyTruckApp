package com.MyTruckApp.service;

import com.MyTruckApp.dto.UpdateDriverDto;
import com.MyTruckApp.model.Company;
import com.MyTruckApp.model.Driver;
import com.MyTruckApp.model.Truck;
import com.MyTruckApp.repository.CompanyRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private TruckRepository truckRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private DriverServiceImpl driverService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnListOfDriversWhenGetAllDriversCalled() {
        // Given
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver());
        drivers.add(new Driver());
        when(driverRepository.findAll()).thenReturn(drivers);

        // When
        List<Driver> result = driverService.getAllDrivers();

        // Then
        assertEquals(2, result.size());
        verify(driverRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnAddedDriverWhenAddDriverCalled() {
        // Given
        Driver driver = new Driver();
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        // When
        Driver result = driverService.addDriver(driver);

        // Then
        assertEquals(driver, result);
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    public void shouldRemoveDriverWhenRemoveByIdCalledWithExistingDriver() {
        // Given
        int driverId = 1;
        Driver driver = new Driver();
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

        // When
        driverService.removeById(driverId);

        // Then
        verify(driverRepository, times(1)).findById(driverId);
        verify(driverRepository, times(1)).deleteById(driverId);
    }

    @Test
    public void shouldNotRemoveDriverWhenRemoveByIdCalledWithNonExistingDriver() {
        // Given
        int driverId = 1;
        when(driverRepository.findById(driverId)).thenReturn(Optional.empty());

        // When
        driverService.removeById(driverId);

        // Then
        verify(driverRepository, times(1)).findById(driverId);
        verify(driverRepository, never()).deleteById(anyInt());
    }

    @Test
    public void shouldSetDriverIsFreeWhenSetIsFreeCalledWithExistingDriver() {
        // Given
        int driverId = 1;
        boolean isFree = true;
        Driver driver = new Driver();
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

        // When
        driverService.setIsFree(isFree, driverId);

        // Then
        verify(driverRepository, times(1)).findById(driverId);
        verify(driverRepository, times(1)).save(driver);
        assertEquals(isFree, driver.isFree());
    }

    @Test
    public void shouldThrowExceptionWhenSetIsFreeCalledWithNonExistingDriver() {
        // Given
        int driverId = 1;
        boolean isFree = true;
        when(driverRepository.findById(driverId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> driverService.setIsFree(isFree, driverId));
        verify(driverRepository, times(1)).findById(driverId);
        verify(driverRepository, never()).save(any(Driver.class));
    }


    @Test
    public void shouldReturnOptionalDriverWhenGetDriverByIdCalledWithExistingDriver() {
        // Given
        int driverId = 1;
        Driver driver = new Driver();
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

        // When
        Optional<Driver> result = driverService.getDriverById(driverId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(driver, result.get());
        verify(driverRepository, times(1)).findById(driverId);
    }

    @Test
    public void shouldThrowExceptionWhenGetDriverByIdCalledWithNonExistingDriver() {
        // Given
        int driverId = 1;
        when(driverRepository.findById(driverId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> driverService.getDriverById(driverId));
        verify(driverRepository, times(1)).findById(driverId);
    }

    @Test
    void shouldUpdateDriverWithValidData() {
        // Given
        Integer driverId = 1;
        UpdateDriverDto updateDriverDto = new UpdateDriverDto();
        updateDriverDto.setTruckId(1);
        updateDriverDto.setCompanyId(1);

        Driver existingDriver = new Driver();
        existingDriver.setId(driverId);

        Truck existingTruck = new Truck();
        existingTruck.setId(updateDriverDto.getTruckId());

        Company existingCompany = new Company();
        existingCompany.setId(updateDriverDto.getCompanyId());

        when(driverRepository.findById(driverId)).thenReturn(Optional.of(existingDriver));
        when(truckRepository.findById(updateDriverDto.getTruckId())).thenReturn(Optional.of(existingTruck));
        when(companyRepository.findById(updateDriverDto.getCompanyId())).thenReturn(Optional.of(existingCompany));

        // When
        driverService.updateDriver(driverId, updateDriverDto);

        // Then
        verify(driverRepository, times(1)).findById(driverId);
        verify(truckRepository, times(1)).findById(updateDriverDto.getTruckId());
        verify(companyRepository, times(1)).findById(updateDriverDto.getCompanyId());
        verify(driverRepository, times(1)).save(existingDriver);

        assertEquals(existingTruck, existingDriver.getTruck());
        assertEquals(existingCompany, existingDriver.getCompany());
    }

    @Test
    void shouldThrowExceptionWhenUpdateDriverWithInvalidData() {
        // Given
        Integer driverId = 1;
        UpdateDriverDto updateDriverDto = new UpdateDriverDto();
        updateDriverDto.setTruckId(1);
        updateDriverDto.setCompanyId(1);

        when(driverRepository.findById(driverId)).thenReturn(Optional.empty());
        when(truckRepository.findById(updateDriverDto.getTruckId())).thenReturn(Optional.empty());
        when(companyRepository.findById(updateDriverDto.getCompanyId())).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> driverService.updateDriver(driverId, updateDriverDto));

        verify(driverRepository, times(1)).findById(driverId);
        verify(truckRepository, times(1)).findById(updateDriverDto.getTruckId());
        verify(companyRepository, times(1)).findById(updateDriverDto.getCompanyId());
        verify(driverRepository, never()).save(any(Driver.class));
    }

}
