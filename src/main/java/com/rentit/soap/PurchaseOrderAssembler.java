package com.rentit.soap;


import org.springframework.beans.factory.annotation.Autowired;

import com.rentit.PurchaseOrder;
import com.rentit.repository.CustomerRepository;

public class PurchaseOrderAssembler {
	
	// TODO: Might be REALLY bad realization
	@Autowired
	CustomerRepository customerRepository;
	
	public WebPurchaseOrderResource toResource(PurchaseOrder po) {

		WebPurchaseOrderResource poResource = new WebPurchaseOrderResource();

		poResource.setStatus(po.getStatus());
		poResource.setStartDate(po.getStartDate());
		poResource.setEndDate(po.getEndDate());
		poResource.setDueDate(po.getDueDate());
		if(po.getPlant() != null) {
			poResource.setPlanName(po.getPlant().getName());
		}
		poResource.setPuchaseID(po.getPuchaseID());
		if(po.getCustomer() != null) {
			poResource.setCustomer(customerRepository.findCustomerName(po.getCustomer().getCustomerId()));
		}

		return poResource;
	}

}
