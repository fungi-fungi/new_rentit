package com.rentit.soap;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@XmlRootElement(name = "plants")
@RooJavaBean
public class PlantResourceCollection {

	// To change representation of object in XML you need to annotait getter.
	private List<PlantResource> listOfPlants;

	public void addPlant(PlantResource resource) {
		if(listOfPlants == null) {
			listOfPlants = new ArrayList<PlantResource>();
		}		
	}
}
