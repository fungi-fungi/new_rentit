package com.rentit.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renit.rest.PlantResource;
import com.renit.rest.PlantResourceAssembler;
import com.renit.rest.PlantResourceList;
import com.rentit.Plant;

@Controller
@RequestMapping("/rest")
public class API {

	@Autowired com.rentit.repository.PlantRepository plantRepository;
	@RequestMapping(method=RequestMethod.GET, value="/plants")
	public ResponseEntity<PlantResourceList> gerPlantsResourcesList() {
		List<Plant> plants = Plant.findAllPlants();

		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = assembler.convertListToResource(plants);

		return new ResponseEntity<PlantResourceList>(resList, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/plant/{id}")
	public ResponseEntity<PlantResource> gerPlantResource(@PathVariable Long id) {
		
		Plant plant = plantRepository.findOne(id);
		PlantResourceAssembler assembler = new PlantResourceAssembler();

		return new ResponseEntity<PlantResource>(assembler.covertPlantToResourse(plant), new HttpHeaders(), HttpStatus.OK);
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/plant")
	public ResponseEntity<Void> createPlantResource(@RequestBody PlantResource plantResource) {
		Plant plant = new Plant();
		plant.setDescription(plantResource.getDescription());
		plant.setName(plantResource.getName());
		plant.setPrice(plantResource.getPrice());
		plant.persist();

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(plant.getId().toString()).build().toUri();

		headers.setLocation(location);

		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}

}
