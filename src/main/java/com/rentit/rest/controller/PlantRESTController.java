package com.rentit.rest.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renit.rest.PlantResource;
import com.rentit.Plant;
import com.rentit.assembler.PlantResourceAssembler;
import com.rentit.repository.PlantRepository;

@Controller
@RequestMapping("/rest/plants/")
public class PlantRESTController {

	@Autowired
	PlantRepository plantRepository;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<PlantResource> getPlantResource(
			@PathVariable Long id,
			@RequestParam(required = false, value = "start") String start,
			@RequestParam(required = false, value = "end") String end) {

		PlantResourceAssembler assembler = new PlantResourceAssembler();		
		Plant plant = plantRepository.findOne(id);

		if (plant == null) {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
		} else {

				if (end != null && start != null) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				try {

					Date startDate = dateFormat.parse(start);
					Date endDate = dateFormat.parse(end);

					if (endDate.after(startDate)) {
						plant = plantRepository.getIfPlantAvaliable(id,	startDate, endDate);
					} else {
						return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
					}
				} catch (ParseException e) {
					return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
				}

			}else if (end != null || start != null){
				return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<PlantResource>(
					assembler.toResource(plant), new HttpHeaders(),
					HttpStatus.OK);
		}
	}

}
