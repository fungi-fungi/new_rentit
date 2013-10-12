package com.renit.rest;

import java.util.Iterator;
import java.util.List;

import com.rentit.Plant;

public class PlantResourceAssembler {

	public PlantResource covertPlantToResourse(Plant plant) {

		PlantResource plantResource = new PlantResource();
		plantResource.name = plant.getName();
		plantResource.price = plant.getPrice();
		plantResource.description = plant.getDescription();
		return plantResource;
	}

	public PlantResourceList convertListToResource(List<Plant> list) {
		PlantResourceList resourcesList = new PlantResourceList();

		Iterator<Plant> i = list.iterator();
		while (i.hasNext()) {
			resourcesList.addPlant(covertPlantToResourse(i.next()));
		}
		return resourcesList;
	}
}
