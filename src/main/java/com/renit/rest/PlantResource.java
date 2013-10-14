package com.renit.rest;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@XmlRootElement(name = "plant")
public class PlantResource {
	
	Long id;

	Float price;

	String name;

	String description;

}
