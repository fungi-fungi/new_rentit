package com.rentit.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renit.rest.PlantResourceList;
import com.rentit.Plant;
import com.rentit.assembler.PlantResourceAssembler;
import com.rentit.dto.DateRangeResource;

@Controller
@RequestMapping("/rest/plants")
public class PlantListRESTController {

	@Autowired
	com.rentit.repository.PlantRepository plantRepository;

	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PlantResourceList> getPlantsResourcesList(@RequestBody(required = false) DateRangeResource dates) {

		List<Plant> plants = new ArrayList<Plant>();

		if (dates != null) {
			plants = plantRepository.getAvailiblePlants(dates.getStart(),
					dates.getEnd());

		} else {
			plants = plantRepository.findAll();
		}

		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = assembler.toResource(plants);

		return new ResponseEntity<PlantResourceList>(resList,
				new HttpHeaders(), HttpStatus.OK);
	}

}
