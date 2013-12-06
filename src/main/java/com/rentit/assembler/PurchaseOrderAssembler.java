package com.rentit.assembler;

import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.renit.rest.InvoiceResource;
import com.renit.rest.PurchaseOrderResource;
import com.renit.rest.PurchaseOrderResourceCollection;
import com.rentit.PurchaseOrder;

public class PurchaseOrderAssembler {

	public PurchaseOrderResource toResource(PurchaseOrder po) {

		PurchaseOrderResource poResource = new PurchaseOrderResource();

		poResource.setStatus(po.getStatus());
		poResource.setStartDate(po.getStartDate());
		poResource.setEndDate(po.getEndDate());
		if(po.getPlant() != null) {
			poResource.setPlantId(po.getPlant().getId());
		}
		poResource.setPuchaseId(po.getId());
		poResource.setDestination(po.getDestination());

		return poResource;
	}
	
	
	public InvoiceResource toInvoiceResource(PurchaseOrder po){
		
		
		InvoiceResource invoice = new InvoiceResource();
		invoice.setClientName(po.getCustomer().getName());
		
		// TODO: Due date is today plus 7 days
		invoice.setDueDate(new DateTime().plusDays(7).toDate());
		invoice.setEmail(po.getCustomer().getEmail());
		invoice.setEndDate(po.getEndDate());
		invoice.setPlantName(po.getPlant().getName());
		invoice.setStartDate(po.getStartDate());
		
		// TODO: Not best place for price calculation
		invoice.setPrice(Days.daysBetween(new DateTime(po.getStartDate()), new DateTime(po.getEndDate())).getDays() * po.getPlant().getPrice());
		invoice.setPurchaseOrder(po.getId());		
		
		return invoice;
		
	}
	
	public PurchaseOrderResourceCollection toResource(List<PurchaseOrder> list) {
		PurchaseOrderResourceCollection resourcesList = new PurchaseOrderResourceCollection();

		Iterator<PurchaseOrder> i = list.iterator();
		while (i.hasNext()) {
			resourcesList.addPlant(toResource(i.next()));
		}
		return resourcesList;
	}

}
