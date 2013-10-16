package com.rentit.rest.controller;


import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.renit.rest.PlantResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@ContextConfiguration(locations="/META-INF/spring/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class PlantRESTContrallerTest {

	private String RESOURCE_URI = "http://localhost:8080/RentIt/rest/plant";
	
    @Autowired
	com.rentit.repository.PlantRepository plantRepository;
    
    PlantResource requestedPlant;
    
    public PlantResource getRequestedPlant() {
		return requestedPlant;
	}

	public void setRequestedPlant(PlantResource requestedPlant) {
		this.requestedPlant = requestedPlant;
	}

	@Before
    public void before(){
    	
    	String plantName = "RTY-12";
    	String description = "Description";
    	float price = 1400f;
    	long plantId = 345346;

		PlantResource resource = new PlantResource();
		resource.setName(plantName);
		resource.setDescription(description);
		resource.setPrice(price);
		resource.setPlantId(plantId);

		Client client = Client.create();
		WebResource webResource = client.resource(RESOURCE_URI);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).post(ClientResponse.class, resource); 

		setRequestedPlant(resource);
		assertTrue(response.getStatus() == Status.CREATED.getStatusCode());
    }
	
	@After
	public void after(){
		
		Client client = Client.create();
		WebResource webResource = client.resource(RESOURCE_URI + getRequestedPlant().getPlantId());
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).delete(ClientResponse.class); 
		assertTrue(response.getStatus() == Status.CREATED.getStatusCode());
	}
    
    @Test
    public void getPlantResource() {
    	
    	Client client = Client.create();
    	WebResource webResource = client.resource(RESOURCE_URI + getRequestedPlant().getPlantId());
    	ClientResponse response = webResource.get(ClientResponse.class);
		
		PlantResource responedPlant = response.getEntity(PlantResource.class); 
		
		assertTrue(responedPlant.getName().equals(getRequestedPlant().getName()));
		assertTrue(responedPlant.getPrice().equals(getRequestedPlant().getPrice()));
		assertTrue(responedPlant.getDescription().equals(getRequestedPlant().getDescription()));
		assertTrue(responedPlant.getPlantId().equals(getRequestedPlant().getPlantId()));

    }
    
    
}
