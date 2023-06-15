package com.MyTruckApp.service;

import com.MyTruckApp.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company addCompany(Company company);

    void removeById(int id);

    Optional<Company> getCompanyById(int id);
}
