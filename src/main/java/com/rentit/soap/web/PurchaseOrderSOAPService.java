package com.rentit.soap.web;

import java.util.Calendar;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.renit.rest.IncomePurchaseOrderResource;
import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.repository.CustomerRepository;
import com.rentit.repository.PlantRepository;

@WebService
public class PurchaseOrderSOAPService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	PlantRepository plantRepository;

	@WebMethod
	public void addPurchaseOrder(@RequestBody IncomePurchaseOrderResource reqest) {
		PurchaseOrder po = new PurchaseOrder();
		po.setId(reqest.getPuchaseID());
		// TODO: Check if plant and customer exist
		po.setCustomer(customerRepository.findOne(reqest.getCustomerId()));
		po.setPlant(plantRepository.findOne(reqest.getPlantId()));
		po.setStatus(PurchaseOrderStatuses.ACCEPTED);
		po.setStartDate(reqest.getStartDate());
		po.setEndDate(reqest.getEndDate());
		
		// DueDate  = end date + 5 days
		Calendar c = Calendar.getInstance(); 
		c.setTime(reqest.getEndDate()); 
		c.add(Calendar.DATE, 5);		;
		po.persist();
	}

}
