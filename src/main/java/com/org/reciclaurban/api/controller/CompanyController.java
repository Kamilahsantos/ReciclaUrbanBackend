package com.org.reciclaurban.api.controller;

import com.org.reciclaurban.api.exception.ResourceNotFoundException;
import com.org.reciclaurban.api.model.Company;
import com.org.reciclaurban.api.repository.CompanyRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class CompanyController {

  @Autowired
  private CompanyRepository companyRepository;

  @ApiOperation(value = "List all companies")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "companies was listed with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("/companies")
  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }


  @ApiOperation(value = "Find a company by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "company find with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("company/{id}")
  public ResponseEntity<Company> geCompanyById(@PathVariable(value = "id") Long companyId)
    throws ResourceNotFoundException {
    Company company = companyRepository.findById(companyId)
      .orElseThrow(() -> new ResourceNotFoundException("Company not found :: " + companyId));
    return ResponseEntity.ok().body(company);
  }

  @ApiOperation(value = "Create a new company")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "company successfully created"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PostMapping("/company")
  public Company createCompany(@Valid @RequestBody Company company) {
    return companyRepository.save(company);
  }


  @ApiOperation(value = "Update a company by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "company updated with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PutMapping("/company/{id}")
  public ResponseEntity < Company > updateCompany(@PathVariable(value = "id") Long companyId,
                                                          @Valid @RequestBody Company companyDetails) throws ResourceNotFoundException {
    Company company = companyRepository.findById(companyId)
      .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));

    company.setAddress(companyDetails.getAddress());
    company.setName(companyDetails.getName());
    company.setTelephone(companyDetails.getTelephone());
    company.setEmail(companyDetails.getEmail());
    company.setPassword(companyDetails.getPassword());
    company.setUsername(companyDetails.getUsername());


    final Company updateCompany = companyRepository.save(company);
    return ResponseEntity.ok(updateCompany);
  }

  @ApiOperation(value = "Delete a company by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "company deleted with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @DeleteMapping("/company/{id}")
  public Map< String, Boolean > deleteCompany(@PathVariable(value = "id") Long companyId)
    throws ResourceNotFoundException {
    Company company = companyRepository.findById(companyId)
      .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));

    companyRepository.delete(company);
    Map< String, Boolean > response = new HashMap< >();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
