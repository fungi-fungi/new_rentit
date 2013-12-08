package com.rentit.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WebPurchaseOrderResourceCollection {

	// To change representation of object in XML you need to annotait getter.
	private List<WebPurchaseOrderResource> plants;

	public void addPlant(WebPurchaseOrderResource resource) {
		if(plants == null) {
			plants = new ArrayList<WebPurchaseOrderResource>();
		}		
		plants.add(resource);
	}
	
	@XmlElement(name = "plants")
	public List<WebPurchaseOrderResource> getPlants() {
		return plants;
	}
}