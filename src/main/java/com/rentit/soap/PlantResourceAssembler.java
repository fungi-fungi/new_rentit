package com.rentit.soap;
import java.util.Iterator;
import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.rentit.soap.PlantResource;
import com.rentit.soap.PlantResourceList;
import com.rentit.Plant;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PlantResourceAssembler {
	public PlantResource toResource(Plant plant) {

		PlantResource plantResource = new PlantResource();
		plantResource.setId(plant.getPlantId());
		plantResource.setName(plant.getName());
		plantResource.setPrice(plant.getPrice());
		plantResource.setDescription(plant.getDescription());
		return plantResource;
	}

	public PlantResourceList toResource(List<Plant> list) {
		PlantResourceList resourcesList = new PlantResourceList();

		Iterator<Plant> i = list.iterator();
		while (i.hasNext()) {
			resourcesList.addPlant(toResource(i.next()));
		}
		return resourcesList;
	}
}
