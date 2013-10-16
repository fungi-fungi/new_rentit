package com.rentit.rest.controller;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import com.renit.rest.PlantResource;
import com.rentit.Customer;
import com.rentit.PurchaseOrder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PurchaseOrderRESTControllerTest {

    private PurchaseOrderRESTController purchaseOrderRESTController = new PurchaseOrderRESTController();

    
    
   
    @Test
    public void cencelPurchaseOrderResource() {
         	
    	
    }

    @Test
    public void modifyPurchaseOrderResource() {
        org.junit.Assert.assertTrue(true);
    }

    @Test
    public void createPurchaseOrderResource() {
        org.junit.Assert.assertTrue(true);
    }
}
