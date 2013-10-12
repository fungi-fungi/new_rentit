package com.renit.rest;

import java.util.List;

import com.rentit.Plant;

public class PlantResourceAssembler {
	
	public PlantResource covertPlantToResourse(Plant plant){
		
		PlantResource plantResource = new PlantResource();
		plantResource.name = plant.getName();
		plantResource.price = plant.getPrice();
		return plantResource;	
	}
	
	public PlantResourceList convertListToResource(List<PlantResource> list){
		return new PlantResourceList(list);
	}

}
