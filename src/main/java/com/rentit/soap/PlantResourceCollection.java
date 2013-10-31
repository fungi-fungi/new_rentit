package com.rentit.soap;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@XmlRootElement
public class PlantResourceCollection {

	// To change representation of object in XML you need to annotait getter.
	@XmlElement
	private List<PlantResource> plants;

	public void addPlant(PlantResource resource) {
		if(plants == null) {
			plants = new ArrayList<PlantResource>();
		}		
	}
}
