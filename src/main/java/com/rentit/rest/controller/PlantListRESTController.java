package com.rentit.rest.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renit.rest.PlantResourceList;
import com.rentit.Plant;
import com.rentit.assembler.PlantResourceAssembler;

@Controller
@RequestMapping("/rest/plants")
public class PlantListRESTController {

	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PlantResourceList> getPlantsResourcesList() {
		List<Plant> plants = Plant.findAllPlants();

		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = assembler.toResource(plants);

		return new ResponseEntity<PlantResourceList>(resList, new HttpHeaders(), HttpStatus.OK);
	}

}
