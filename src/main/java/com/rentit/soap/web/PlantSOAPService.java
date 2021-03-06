package com.rentit.soap.web;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentit.Plant;
import com.rentit.dto.DateRangeResource;
import com.rentit.soap.PlantResourceAssembler;
import com.rentit.soap.PlantResourceCollection;

@WebService
public class PlantSOAPService {
	
	@Autowired
	com.rentit.repository.PlantRepository plantRepository;
	
	@WebMethod
	public PlantResourceCollection getAvailiblePlants(@RequestBody DateRangeResource reqest){

		List<Plant> listOfAvailiblePlants = plantRepository.findAvailiblePlants(reqest.getStart(), reqest.getEnd());
		
		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceCollection result = assembler.toResource(listOfAvailiblePlants);
		
		return result;
	}
}
