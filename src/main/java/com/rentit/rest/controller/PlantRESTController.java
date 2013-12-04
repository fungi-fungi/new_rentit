package com.rentit.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renit.rest.PlantResource;
import com.rentit.Plant;
import com.rentit.assembler.PlantResourceAssembler;

@Controller
@RequestMapping("/rest/plant")
public class PlantRESTController {

	@Autowired
	com.rentit.repository.PlantRepository plantRepository;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<PlantResource> getPlantResource(@PathVariable Long id) {

		Plant plant = plantRepository.findOne(id);
		PlantResourceAssembler assembler = new PlantResourceAssembler();
		
		return new ResponseEntity<PlantResource>(
				assembler.toResource(plant), new HttpHeaders(),	HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<Void> incertPlantResource(@RequestBody PlantResource plantResource) {
		
		Plant plant = new Plant();
		plant.setId(plantResource.getPlantId());
		plant.setName(plantResource.getName());
		plant.setDescription(plantResource.getDescription());
		plant.setPrice(plantResource.getPrice());
		plant.persist();
		
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(plant.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deletePlantResource(@PathVariable Long id) {

		
		Plant plant = plantRepository.findOne(id);
		
		plantRepository.delete(plant);

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(plant.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}

}
