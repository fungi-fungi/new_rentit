package com.rentit.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(method=RequestMethod.GET, value="/plants")
	public ResponseEntity<PlantResourceList> gerPlantsResourcesList() {
		List<Plant> pos = Plant.findAllPlants();

		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = assembler.convertListToResource(pos);

		return new ResponseEntity<PlantResourceList>(resList, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/plant")
	public ResponseEntity<Void> createPlantResource(@RequestBody PlantResource res) {
		Plant p = new Plant();
		p.setDescription(res.getDescription());
		p.setName(res.getName());
		p.setPrice(res.getPrice());
		p.persist();

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(p.getId().toString()).build().toUri();

		headers.setLocation(location);

		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}

}
