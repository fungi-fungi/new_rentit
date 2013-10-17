package com.rentit.rest.controller;


import static org.junit.Assert.*;

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

	private String RESOURCE_URI = "http://rentit-2.herokuapp.com/rest/plant/";
	
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

		PlantResource plantResource = new PlantResource();
		plantResource.setName(plantName);
		plantResource.setDescription(description);
		plantResource.setPrice(price);
		plantResource.setPlantId(plantId);

		Client client = Client.create();
		WebResource webResource = client.resource(RESOURCE_URI);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).post(ClientResponse.class, plantResource); 

		setRequestedPlant(plantResource);
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
    }
	
	@After
	public void after(){
		
		Client client = Client.create();
		WebResource webResource = client.resource(RESOURCE_URI + getRequestedPlant().getPlantId());
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).delete(ClientResponse.class); 
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
	}
    
    @Test
    public void getPlantResource() {
    	
    	Client client = Client.create();
    	WebResource webResource = client.resource(RESOURCE_URI + getRequestedPlant().getPlantId());
    	ClientResponse response = webResource.get(ClientResponse.class);
		
		PlantResource responedPlant = response.getEntity(PlantResource.class); 
		
		assertEquals(responedPlant.getName(), getRequestedPlant().getName());
		assertEquals(responedPlant.getPrice(), getRequestedPlant().getPrice());
		assertEquals(responedPlant.getDescription(), getRequestedPlant().getDescription());
		assertEquals(responedPlant.getPlantId(), getRequestedPlant().getPlantId());

    }
    
    
}
