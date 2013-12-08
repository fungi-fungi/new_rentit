package com.rentit.rest;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.util.ResourceSupport;

@RooJavaBean
@XmlRootElement(name = "plant")
public class PlantResource extends ResourceSupport {
	
	Long plantId;

	Float price;

	String name;

	String description;

}
