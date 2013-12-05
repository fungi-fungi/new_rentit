package com.renit.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pos")
public class PurchaseOrderResourceList {

	// To change representation of object in XML you need to annotait getter.
	private List<PurchaseOrderResource> listOfPlants;

	@XmlElement(name = "po")
	public List<PurchaseOrderResource> getListOfPlants() {
		return listOfPlants;
	}

	public void setListOfPlants(List<PurchaseOrderResource> listOfPlants) {
		this.listOfPlants = listOfPlants;
	}

	public void addPlant(PurchaseOrderResource plant) {
		this.listOfPlants.add(plant);
	}

	public PurchaseOrderResourceList() {
		listOfPlants = new ArrayList<PurchaseOrderResource>();
	}

	public PurchaseOrderResourceList(List<PurchaseOrderResource> listOfResourses) {
		this.listOfPlants = new ArrayList<PurchaseOrderResource>(
				listOfResourses);
	}
}
