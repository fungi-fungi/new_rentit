package com.rentit.rest.controller;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.renit.rest.PlantResource;
import com.renit.rest.PlantResourceList;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


@ContextConfiguration(locations="/META-INF/spring/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class PlantListRESTControllerTest {
   
    private String RESOURCE_URI = "http://rentit-2.herokuapp.com/rest/plants";
    
    @Test
    public void getPlantsResourcesList() {
    	
    	Client client = Client.create();
    	WebResource webResource = client.resource(RESOURCE_URI);
    	ClientResponse response = webResource.get(ClientResponse.class);
		
    	assertEquals(response.getStatus(), Status.OK.getStatusCode());	
    }
}
