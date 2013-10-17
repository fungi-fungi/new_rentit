package com.renit.rest;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@XmlRootElement(name = "plant")
public class PlantResource {
	
	Long plantId;

	Float price;

	String name;

	String description;

}
