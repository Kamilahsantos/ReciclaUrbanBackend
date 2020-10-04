package com.org.reciclaurban.api.controller;

import com.org.reciclaurban.api.exception.ResourceNotFoundException;
import com.org.reciclaurban.api.model.Activity;
import com.org.reciclaurban.api.repository.ActivityRepository;
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
public class ActivityController {

  @Autowired
  private ActivityRepository activityRepository;

  @ApiOperation(value = "List all activities")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "activities was listed with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("/activities")
  public List<Activity> getAllActivities() {
    return activityRepository.findAll();
  }


  @ApiOperation(value = "Find a active by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "activity find with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @GetMapping("activity/{id}")
  public ResponseEntity<Activity> getActivityById(@PathVariable(value = "id") Long activityId)
    throws ResourceNotFoundException {
    Activity activity = activityRepository.findById(activityId)
      .orElseThrow(() -> new ResourceNotFoundException("Activity not found :: " + activityId));
    return ResponseEntity.ok().body(activity);
  }

  @ApiOperation(value = "Create a new activity")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "activity successfully created"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PostMapping("/activity")
  public Activity createActivity(@Valid @RequestBody Activity activity) {
    return activityRepository.save(activity);
  }


  @ApiOperation(value = "Update a activity by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "company updated with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @PutMapping("/activity/{id}")
  public ResponseEntity < Activity > updateActivity(@PathVariable(value = "id") Long activityId,
                                                  @Valid @RequestBody Activity activityDetails) throws ResourceNotFoundException {
    Activity activity = activityRepository.findById(activityId)
      .orElseThrow(() -> new ResourceNotFoundException("Activity not found for this id :: " + activityId));

    activity.setCategory(activityDetails.getCategory());
    activity.setDescription(activityDetails.getDescription());
    activity.setPoint(activityDetails.getPoint());
    activity.setTitle(activity.getTitle());
    activity.setUserId(activityDetails.getUserId());

    final Activity updateActivity = activityRepository.save(activity);
    return ResponseEntity.ok(updateActivity);
  }

  @ApiOperation(value = "Delete a activity by Id")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "activity deleted with success"),
    @ApiResponse(code = 500, message = "An internal error occurred while creating the claim, it was not possible to complete your request")
  }
  )
  @DeleteMapping("/activity/{id}")
  public Map< String, Boolean > deleteActivity(@PathVariable(value = "id") Long activityId)
    throws ResourceNotFoundException {
    Activity activity = activityRepository.findById(activityId)
      .orElseThrow(() -> new ResourceNotFoundException("Activity not found for this id :: " + activityId));

    activityRepository.delete(activity);
    Map< String, Boolean > response = new HashMap< >();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
