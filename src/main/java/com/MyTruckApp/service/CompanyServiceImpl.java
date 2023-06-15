package com.MyTruckApp.service;

import com.MyTruckApp.model.Company;
import com.MyTruckApp.model.Driver;
import com.MyTruckApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


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
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> getCompanyById(int id) {
        Optional<Company> optional = companyRepository.findById(id);
        if(optional.isEmpty())
        {
            throw new RuntimeException("Nie znaleziono firmy o id: " + id);
        }
        return optional;
    }
}
