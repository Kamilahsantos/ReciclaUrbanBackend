package com.org.reciclaurban.api.controller;

import com.org.reciclaurban.api.exception.ResourceNotFoundException;
import com.org.reciclaurban.api.model.Condominium;
import com.org.reciclaurban.api.repository.CondominiumRepository;
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
public class CondominiumController {

  @Autowired
  private CondominiumRepository condominiumRepository;

  @ApiOperation(value = "List all codominium")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "condominium was listed with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("/condominiums")
  public List<Condominium> getAllCondominiums() {
    return condominiumRepository.findAll();
  }


  @ApiOperation(value = "Find a condominium by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "condominium find with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("condominium/{id}")
  public ResponseEntity<Condominium> geCondominiumById(@PathVariable(value = "id") Long condominiumId)
    throws ResourceNotFoundException {
    Condominium condominium = condominiumRepository.findById(condominiumId)
      .orElseThrow(() -> new ResourceNotFoundException("Company not found :: " + condominiumId));
    return ResponseEntity.ok().body(condominium);
  }

  @ApiOperation(value = "Create a new condominium")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "condominium successfully created"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PostMapping("/condominium")
  public Condominium createCondominium(@Valid @RequestBody Condominium condominium) {
    return condominiumRepository.save(condominium);
  }


  @ApiOperation(value = "Update a condominium by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "condominium updated with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PutMapping("/condominium/{id}")
  public ResponseEntity < Condominium > updateCondominium(@PathVariable(value = "id") Long condominiumId,
                                                  @Valid @RequestBody Condominium condominiumDetails) throws ResourceNotFoundException {
    Condominium condominium = condominiumRepository.findById(condominiumId)
      .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + condominiumId));

    condominium.setAddress(condominiumDetails.getAddress());
    condominium.setEmail(condominiumDetails.getEmail());
    condominium.setName(condominiumDetails.getName());
    condominium.setPassword(condominiumDetails.getPassword());
    condominium.setScore(condominiumDetails.getScore());
    condominium.setTelephone(condominiumDetails.getTelephone());



    final Condominium updateCondominium = condominiumRepository.save(condominium);
    return ResponseEntity.ok(condominium);
  }

  @ApiOperation(value = "Delete a condominium by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "condominium deleted with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @DeleteMapping("/condominium/{id}")
  public Map< String, Boolean > deleteCondominium(@PathVariable(value = "id") Long condominiumId)
    throws ResourceNotFoundException {
    Condominium condominium = condominiumRepository.findById(condominiumId)
      .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + condominiumId));

    condominiumRepository.delete(condominium);
    Map< String, Boolean > response = new HashMap< >();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
