package com.rentit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renit.rest.PlantResource;
import com.renit.rest.PlantResourceAssembler;
import com.rentit.Plant;

@Controller
@RequestMapping("/rest/plant")
public class PlantRESTContraller {

	@Autowired
	com.rentit.repository.PlantRepository plantRepository;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<PlantResource> gerPlantResource(@PathVariable Long id) {

		Plant plant = plantRepository.findOne(id);
		PlantResourceAssembler assembler = new PlantResourceAssembler();

		return new ResponseEntity<PlantResource>(
				assembler.covertPlantToResourse(plant), new HttpHeaders(),
				HttpStatus.OK);
	}

}
