package com.rentit.soap.web;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentit.Plant;
import com.rentit.soap.DateRangeResource;
import com.rentit.soap.PlantResourceAssembler;
import com.rentit.soap.PlantResourceCollection;

@WebService
public class PlantSOAPService {
	
	@Autowired
	com.rentit.repository.PlantRepository plantRepository;
	
	@WebMethod
	public PlantResourceCollection getAvailiblePlants(@RequestBody DateRangeResource reqest){
		
		List<Plant> listOfAvailiblePlants = plantRepository.getAvailiblePlant(reqest.getStart(), reqest.getEnd());
		
		PlantResourceAssembler assembler = new PlantResourceAssembler();
		
		return assembler.toResource(listOfAvailiblePlants);
		
	}
}
