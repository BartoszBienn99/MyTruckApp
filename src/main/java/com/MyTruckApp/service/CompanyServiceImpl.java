package com.MyTruckApp.service;

import com.MyTruckApp.exception_handler.IdNotFoundException;
import com.MyTruckApp.model.Company;
import com.MyTruckApp.model.Driver;
import com.MyTruckApp.repository.CompanyRepository;
import com.MyTruckApp.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void removeById(int id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            for (Driver driver : optionalCompany.get().getDrivers()) {
                driver.setCompany(null);
                driverRepository.save(driver);
            }
            companyRepository.deleteById(id);
        }else {
            throw new IdNotFoundException("Nie znaleziono firmy o id: " + id);
        }
    }

    @Override
    public Optional<Company> getCompanyById(int id) {
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotFoundException("Nie znaleziono firmy o id: " + id);
        }
        return optional;
    }
}
