package com.rentit.assembler;

import java.util.Iterator;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.rentit.Plant;
import com.rentit.rest.PlantResource;
import com.rentit.rest.PlantResourceCollection;
import com.rentit.rest.controller.PlantRESTController;

public class PlantResourceAssembler extends ResourceAssemblerSupport<Plant, PlantResource> {
	
	public PlantResourceAssembler() { 
		 super(PlantRESTController.class, PlantResource.class); 
		} 

	public PlantResource toResource(Plant plant) {

		PlantResource plantResource = createResourceWithId(plant.getId(), plant);
		plantResource.setPlantId(plant.getId());
		plantResource.setName(plant.getName());
		plantResource.setPrice(plant.getPrice());
		plantResource.setDescription(plant.getDescription());
		return plantResource;
	}

	public PlantResourceCollection toResource(List<Plant> list) {
		PlantResourceCollection resourcesList = new PlantResourceCollection();

		Iterator<Plant> i = list.iterator();
		while (i.hasNext()) {
			resourcesList.addPlant(toResource(i.next()));
		}
		return resourcesList;
	}
}
