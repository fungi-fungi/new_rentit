package com.rentit.soap.web;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.Plant;
import com.rentit.soap.PlantResourceAssembler;
import com.rentit.soap.PlantResourceList;

@RooJavaBean
@WebService
public class PlantSOAPService {
	@WebMethod
	public PlantResourceList getAllPlants(){
		PlantResourceList resourceList = new PlantResourceList();
		List<Plant> plants = Plant.findAllPlants();
		PlantResourceAssembler assembler = new PlantResourceAssembler();
		PlantResourceList resList = assembler.toResource(plants);
		return resourceList;
	}
}
