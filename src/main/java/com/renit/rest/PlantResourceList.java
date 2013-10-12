package com.renit.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@XmlRootElement(name = "plants")
public class PlantResourceList {

	private List<PlantResource> listOfPlants;

	public void addPlant(PlantResource plant) {
		this.listOfPlants.add(plant);
	}

	public PlantResourceList() {
		listOfPlants = new ArrayList<PlantResource>();
	}

	public PlantResourceList(List<PlantResource> listOfResourses) {
		this.listOfPlants = new ArrayList<PlantResource>(listOfResourses);
	}

}
