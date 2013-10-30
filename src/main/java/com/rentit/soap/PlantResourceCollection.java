package com.rentit.soap;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "plants")
public class PlantResourceCollection {

	// To change representation of object in XML you need to annotait getter.
	private List<PlantResource> listOfPlants;

	@XmlElement(name = "plant")
	public List<PlantResource> getListOfPlants() {
		return listOfPlants;
	}

	public void setListOfPlants(List<PlantResource> listOfPlants) {
		this.listOfPlants = listOfPlants;
	}

	public void addPlant(PlantResource plant) {
		this.listOfPlants.add(plant);
	}

	public PlantResourceCollection() {
		listOfPlants = new ArrayList<PlantResource>();
	}

	public PlantResourceCollection(List<PlantResource> listOfResourses) {
		this.listOfPlants = new ArrayList<PlantResource>(listOfResourses);
	}
}
