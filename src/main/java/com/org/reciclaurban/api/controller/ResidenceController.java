package com.org.reciclaurban.api.controller;


import com.org.reciclaurban.api.exception.ResourceNotFoundException;
import com.org.reciclaurban.api.model.Residence;
import com.org.reciclaurban.api.repository.ResidenceRepository;
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
public class ResidenceController {

  @Autowired
  private ResidenceRepository residenceRepository;

  @ApiOperation(value = "List all residence")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "residence was listed with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("/residence")
  public List<Residence> getAllResidence() {
    return residenceRepository.findAll();
  }


  @ApiOperation(value = "Find a residence by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "residence find with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("residence/{id}")
  public ResponseEntity<Residence> getResidenceById(@PathVariable(value = "id") Long residenceId)
    throws ResourceNotFoundException {
    Residence residence = residenceRepository.findById(residenceId)
      .orElseThrow(() -> new ResourceNotFoundException("Residence not found :: " + residenceId));
    return ResponseEntity.ok().body(residence);
  }

  @ApiOperation(value = "Create a new residence")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "residence successfully created"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PostMapping("/residence")
  public Residence createResidence(@Valid @RequestBody Residence residence) {
    return residenceRepository.save(residence);
  }


  @ApiOperation(value = "Update a residence by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "residence updated with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PutMapping("/residence/{id}")
  public ResponseEntity < Residence > updateResidence(@PathVariable(value = "id") Long residenceId,
                                                          @Valid @RequestBody Residence residenceDetails) throws ResourceNotFoundException {
    Residence residence = residenceRepository.findById(residenceId)
      .orElseThrow(() -> new ResourceNotFoundException("Residence not found for this id :: " + residenceId));

    residence.setAddress(residenceDetails.getAddress());
    residence.setEmail(residenceDetails.getEmail());
    residence.setName(residenceDetails.getName());
    residence.setPassword(residenceDetails.getPassword());
    residence.setTelephone(residenceDetails.getPassword());




    final Residence updateResidence = residenceRepository.save(residence);
    return ResponseEntity.ok(residence);
  }

  @ApiOperation(value = "Delete a residence by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "residence deleted with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @DeleteMapping("/residence/{id}")
  public Map< String, Boolean > deleteResidence(@PathVariable(value = "id") Long residenceId)
    throws ResourceNotFoundException {
    Residence residence = residenceRepository.findById(residenceId)
      .orElseThrow(() -> new ResourceNotFoundException("Residence not found for this id :: " + residenceId));

    residenceRepository.delete(residence);
    Map< String, Boolean > response = new HashMap< >();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
