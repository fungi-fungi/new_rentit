package com.rentit.rest.controller;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.renit.rest.CustomerResource;
import com.renit.rest.PlantResource;
import com.renit.rest.PurchaseOrderResource;
import com.rentit.PurchaseOrderStatuses;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PurchaseOrderRESTControllerTest {

	private String PLANT_RESOURCE_URI = "http://rentit-2.herokuapp.com/rest/plant/";
	private String CUSTOMER_RESOURCE_URI = "http://rentit-2.herokuapp.com/rest/customer/";
	private String PURCHASE_ORDER_RESOURCE_URI = "http://rentit-2.herokuapp.com/rest/po/";
	private String CANCEL_PURCHASE_ORDER = "/accept";
	
	
	private CustomerResource customerRes;
	private PlantResource plantRes;
	private PurchaseOrderResource poRes;
	
	public PurchaseOrderResource getPoRes() {
		return poRes;
	}
	
	public void setPoRes(PurchaseOrderResource poRes) {
		this.poRes = poRes;
	}
	
	public CustomerResource getCustomerRes() {
		return customerRes;
	}

	public void setCustomerRes(CustomerResource customerRes) {
		this.customerRes = customerRes;
	}

	public PlantResource getPlantRes() {
		return plantRes;
	}

	public void setPlantRes(PlantResource plantRes) {
		this.plantRes = plantRes;
	}

    @Before
    public void before(){
    	
    	String plantName = "RTY-12";
    	String description = "Description";
    	float price = 1400f;
    	long plantId = 9999999;
    	
		PlantResource plant = new PlantResource();
		plant.setName(plantName);
		plant.setDescription(description);
		plant.setPrice(price);
		plant.setPlantId(plantId);
		
		Client client = Client.create();
		WebResource webResource = client.resource(PLANT_RESOURCE_URI);
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).post(ClientResponse.class, plant); 
		setPlantRes(plant);
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		
		
		long customerId = 9999999;
		String customerName = "Test";
		
		CustomerResource customer = new CustomerResource();
		customer.setCustomerId(customerId);
		customer.setName(customerName);

		client = Client.create();
		webResource = client.resource(CUSTOMER_RESOURCE_URI);
		response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).post(ClientResponse.class, customer); 
		
		setCustomerRes(customer);
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());		
		
		
		long purchaseId = 999999;
		PurchaseOrderStatuses status = PurchaseOrderStatuses.ACCEPTED;
		Calendar startDate = GregorianCalendar.getInstance();
		startDate.set(2013, 10, 10, 12, 00, 00);
		Calendar endDate = GregorianCalendar.getInstance();
		endDate.set(2013, 10, 15, 12, 00, 00);
		Calendar dueDate = GregorianCalendar.getInstance();
		endDate.set(2013, 10, 25, 12, 00, 00);
		
		
		PurchaseOrderResource poResource = new PurchaseOrderResource();
		poResource.setEndDate(endDate.getTime());
		poResource.setPlantId(plantId);
		poResource.setPuchaseId(purchaseId);
		poResource.setStartDate(startDate.getTime());
		poResource.setStatus(status);
		
	
		client = Client.create();
		webResource = client.resource(PURCHASE_ORDER_RESOURCE_URI);
		response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).post(ClientResponse.class, poResource); 
		
		setPoRes(poResource);
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		
    }
    
    @After
	public void after(){
    	
    	Client client = Client.create();
    	WebResource webResource = client.resource(PURCHASE_ORDER_RESOURCE_URI + getPoRes().getPuchaseId());
    	ClientResponse response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).delete(ClientResponse.class); 
    	assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		
		client = Client.create();
		webResource = client.resource(PLANT_RESOURCE_URI + getPlantRes().getPlantId());
		response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).delete(ClientResponse.class); 
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		
		client = Client.create();
		webResource = client.resource(CUSTOMER_RESOURCE_URI + getCustomerRes().getCustomerId());
		response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).delete(ClientResponse.class); 
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());		
	}
    
    @Test
    public void createPurchaseOrderResource() {
    	Client client = Client.create();
    	WebResource webResource = client.resource(PURCHASE_ORDER_RESOURCE_URI + getPoRes().getPuchaseId());
    	ClientResponse response = webResource.get(ClientResponse.class);
		
    	PurchaseOrderResource poResource = response.getEntity(PurchaseOrderResource.class); 
		
    	assertEquals(poResource.getStatus(), getPoRes().getStatus());
    	assertEquals(poResource.getStartDate(), getPoRes().getStartDate());
    	assertEquals(poResource.getPuchaseId(), getPoRes().getPuchaseId());
    	assertEquals(poResource.getPlantId(), getPoRes().getPlantId());
    	assertEquals(poResource.getEndDate(), getPoRes().getEndDate() );
    }
    
    @Test
    public void modifyPurchaseOrderResource() {
    	
    	Calendar startDate = GregorianCalendar.getInstance();
		startDate.set(2014, 10, 10, 12, 00, 00);
		Calendar endDate = GregorianCalendar.getInstance();
		endDate.set(2014, 10, 15, 12, 00, 00);
		Calendar dueDate = GregorianCalendar.getInstance();
		endDate.set(2014, 10, 25, 12, 00, 00);
    	
    	PurchaseOrderResource newPoResource = getPoRes();
    	newPoResource.setEndDate(endDate.getTime());
    	newPoResource.setStartDate(startDate.getTime());
    	
    	Client client = Client.create();
    	WebResource webResource = client.resource(PURCHASE_ORDER_RESOURCE_URI + newPoResource.getPuchaseId());
    	ClientResponse response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).post(ClientResponse.class, newPoResource); 
    	assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
    	
	    client = Client.create();
	   	webResource = client.resource(PURCHASE_ORDER_RESOURCE_URI + getPoRes().getPuchaseId());
	   	response = webResource.get(ClientResponse.class);
			
	   	PurchaseOrderResource poResource = response.getEntity(PurchaseOrderResource.class); 
			
	   	assertEquals(poResource.getStatus(), newPoResource.getStatus());
	   	assertEquals(poResource.getStartDate(), newPoResource.getStartDate());
	   	assertEquals(poResource.getPuchaseId(), newPoResource.getPuchaseId());
	   	assertEquals(poResource.getPlantId(), newPoResource.getPlantId());
	   	assertEquals(poResource.getEndDate(), newPoResource.getEndDate() );
    }
    
    @Test
    public void cencelPurchaseOrderResource() {   
    	
    	Client client = Client.create();
    	WebResource webResource = client.resource(PURCHASE_ORDER_RESOURCE_URI +
    			getPoRes().getPuchaseId() + CANCEL_PURCHASE_ORDER); 	
    	ClientResponse response = webResource.type(MediaType.APPLICATION_XML) 
				 .accept(MediaType.APPLICATION_XML).delete(ClientResponse.class);  
    	
    	
    	webResource = client.resource(PURCHASE_ORDER_RESOURCE_URI + getPoRes().getPuchaseId());
    	response = webResource.get(ClientResponse.class);
		
    	PurchaseOrderResource poResource = response.getEntity(PurchaseOrderResource.class); 
		
    	assertEquals(poResource.getStatus(), PurchaseOrderStatuses.CANCELED);
    	
    }
}
