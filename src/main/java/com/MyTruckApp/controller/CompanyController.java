package com.MyTruckApp.controller;

import com.MyTruckApp.model.Company;
import com.MyTruckApp.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;


    @GetMapping
    public List<Company> getALlCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        Optional<Company> response = companyService.getCompanyById(id);
        return response
                .map(companyResponse -> ResponseEntity.ok(companyResponse))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Company> addNewCompany(@RequestBody Company requestCompany){
        Company savedCompany = companyService.addCompany(requestCompany);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCompany);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> removeCompanyById(@PathVariable int id) {
        if(companyService.getCompanyById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            companyService.removeById(id);
            return ResponseEntity.noContent().build();
        }
    }



}
