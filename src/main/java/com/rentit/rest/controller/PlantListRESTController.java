package com.rentit.rest.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renit.rest.PlantResourceList;
import com.rentit.Plant;
import com.rentit.assembler.PlantResourceAssembler;

@Controller
@RequestMapping("/rest/plants")
public class PlantListRESTController {

	@Autowired
	com.rentit.repository.PlantRepository plantRepository;

	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PlantResourceList> getPlantsResourcesList(
			@RequestParam(required = false, value = "start") String start,
			@RequestParam(required = false, value = "end") String end) {

		List<Plant> plants = new ArrayList<Plant>();

		//TODO: Think about custom Exceptions
		
		if (end != null && start != null) {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			try {
				
				Date startDate = dateFormat.parse(start);
				Date endDate  = dateFormat.parse(end);
				
				if(endDate.after(startDate)){
					plants = plantRepository.getAvailiblePlants(startDate, endDate);
				}else{
					return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
				}
				
			} catch (ParseException e) {
				return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
			}

		} else if (end == null && start == null) {
			plants = plantRepository.findAll();
		} else{
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}

		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = assembler.toResource(plants);

		return new ResponseEntity<PlantResourceList>(resList, new HttpHeaders(), HttpStatus.OK);
	}
	
/*	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PlantResourceList> getPlantsResourcesList() {

		List<Plant> plants = plantRepository.findAll();
			
		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = assembler.toResource(plants);

		return new ResponseEntity<PlantResourceList>(resList, new HttpHeaders(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PlantResourceList> getPlantsResourcesList(
			@RequestParam(required = true, value = "start") String start,
			@RequestParam(required = true, value = "end") String end) {

		List<Plant> plants = new ArrayList<Plant>();

		//TODO: Think about custom Exceptions
		
		if (end != null && start != null) {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			try {
				plants = plantRepository.getAvailiblePlants(dateFormat.parse(start), dateFormat.parse(end));
			} catch (ParseException e) {
				return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
			}
		}
			
		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = assembler.toResource(plants);

		return new ResponseEntity<PlantResourceList>(resList, new HttpHeaders(), HttpStatus.OK);
	}*/

}
