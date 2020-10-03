package com.org.reciclaurban.api.controller;

import com.org.reciclaurban.api.exception.ResourceNotFoundException;
import com.org.reciclaurban.api.model.Cooperative;
import com.org.reciclaurban.api.repository.CooperativeRepository;
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
public class CooperativeController {

  @Autowired
  private CooperativeRepository cooperativeRepository;

  @ApiOperation(value = "List all cooperatives")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "cooperative was listed with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("/cooperatives")
  public List<Cooperative> getAllCooperatives() {
    return cooperativeRepository.findAll();
  }


  @ApiOperation(value = "Find a cooperative by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "cooperative find wiht success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("cooperative/{id}")
  public ResponseEntity<Cooperative> geCooperativeById(@PathVariable(value = "id") Long cooperativeId)
    throws ResourceNotFoundException {
    Cooperative cooperative = cooperativeRepository.findById(cooperativeId)
      .orElseThrow(() -> new ResourceNotFoundException("Cooperative not found :: " + cooperativeId));
    return ResponseEntity.ok().body(cooperative);
  }

  @ApiOperation(value = "Create a new cooperative")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "cooperative successfully created"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PostMapping("/cooperative")
  public Cooperative createCooperative(@Valid @RequestBody Cooperative cooperative) {
    return cooperativeRepository.save(cooperative);
  }


  @ApiOperation(value = "Update a cooperative by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "cooperative updated with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PutMapping("/cooperative/{id}")
  public ResponseEntity < Cooperative > updateCooperative(@PathVariable(value = "id") Long cooperativeId,
                                            @Valid @RequestBody Cooperative cooperativeDetails) throws ResourceNotFoundException {
    Cooperative cooperative = cooperativeRepository.findById(cooperativeId)
      .orElseThrow(() -> new ResourceNotFoundException("Cooperative not found for this id :: " + cooperativeId));

    cooperative.setAddress(cooperativeDetails.getAddress());
    cooperative.setCategory(cooperativeDetails.getCategory());
    cooperative.setName(cooperativeDetails.getName());
    cooperative.setSite(cooperativeDetails.getSite());
    cooperative.setTelephone(cooperativeDetails.getTelephone());

    final Cooperative updateCooperative = cooperativeRepository.save(cooperative);
    return ResponseEntity.ok(updateCooperative);
  }

  @ApiOperation(value = "Delete a cooperative by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "cooperative deleted with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @DeleteMapping("/rooms/{id}")
  public Map< String, Boolean > deleteCooperative(@PathVariable(value = "id") Long cooperativeId)
    throws ResourceNotFoundException {
    Cooperative cooperative = cooperativeRepository.findById(cooperativeId)
      .orElseThrow(() -> new ResourceNotFoundException("Cooperative not found for this id :: " + cooperativeId));

    cooperativeRepository.delete(cooperative);
    Map< String, Boolean > response = new HashMap< >();
    response.put("deleted", Boolean.TRUE);
    return response;
  }


}
