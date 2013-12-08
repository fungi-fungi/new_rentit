package com.rentit.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentit.Plant;
import com.rentit.assembler.PlantResourceAssembler;
import com.rentit.dto.ErrorResource;
import com.rentit.exception.InvalidHirePeriodException;
import com.rentit.exception.PlantUnavailableException;
import com.rentit.exception.ResourceNotFoundException;
import com.rentit.rest.PlantResource;
import com.rentit.rest.PlantResourceCollection;
import com.rentit.service.PlantHelperService;

@Controller
@RequestMapping("/rest/plants")
public class PlantRESTController {

	@Autowired
	PlantHelperService plantService;

	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PlantResourceCollection> getPlantsResourcesList(
			@RequestParam(required = false, value = "start") String start,
			@RequestParam(required = false, value = "end") String end)
			throws InvalidHirePeriodException {

		List<Plant> plants = plantService.getAllAvaliblePlants(start, end);
		PlantResourceAssembler assembler = new PlantResourceAssembler();

		return new ResponseEntity<PlantResourceCollection>(assembler.toResource(plants), new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PlantResource> getPlantResource(
			@PathVariable Long id,
			@RequestParam(required = false, value = "start") String start,
			@RequestParam(required = false, value = "end") String end)
			throws PlantUnavailableException, InvalidHirePeriodException {

		Plant plant = plantService.getPlantIfAvaliable(id, start, end);
		PlantResourceAssembler assembler = new PlantResourceAssembler();

		return new ResponseEntity<PlantResource>(assembler.toResource(plant), new HttpHeaders(), HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidHirePeriodException.class)
	public ResponseEntity<ErrorResource> handleInvalidDatePeriod(ResourceNotFoundException ex) {
		
		ErrorResource error = new ErrorResource();
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());

		return new ResponseEntity<ErrorResource>(error, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(PlantUnavailableException.class)
	public ResponseEntity<ErrorResource> handlePlantUnavailable(PlantUnavailableException ex) {
		
		ErrorResource error = new ErrorResource();
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ErrorResource>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}
