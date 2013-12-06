package com.rentit.assembler;

import java.util.Iterator;
import java.util.List;

import com.renit.rest.PlantResource;
import com.renit.rest.PlantResourceCollection;
import com.rentit.Plant;

public class PlantResourceAssembler {

	public PlantResource toResource(Plant plant) {

		PlantResource plantResource = new PlantResource();
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
