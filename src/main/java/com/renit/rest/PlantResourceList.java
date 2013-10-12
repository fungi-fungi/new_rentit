package com.renit.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@XmlRootElement(name = "plants")
public class PlantResourceList {

	List<PlantResource> listOfResourses;

	public PlantResourceList() {

	}

	public PlantResourceList(List<PlantResource> listOfResourses) {
		this.listOfResourses = listOfResourses;
	}

}
