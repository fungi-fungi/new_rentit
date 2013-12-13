package com.rentit.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.rest.InputPurchaseOrderResource;
import com.rentit.rest.InvoiceResource;
import com.rentit.rest.PurchaseOrderResource;
import com.rentit.rest.PurchaseOrderResourceCollection;
import com.rentit.rest.controller.PurchaseOrderRESTController;
import com.rentit.util.ExtendedLink;

public class PurchaseOrderAssembler extends ResourceAssemblerSupport<PurchaseOrder, PurchaseOrderResource>  {
	
	public PurchaseOrderAssembler() { 
		 super(PurchaseOrderRESTController.class, PurchaseOrderResource.class); 
		} 


	public PurchaseOrderResource toResource(PurchaseOrder po)  {

		PurchaseOrderResource poResource = createResourceWithId(po.getId(), po);

		poResource.setStatus(po.getStatus());
		poResource.setStartDate(po.getStartDate());
		poResource.setEndDate(po.getEndDate());
		if(po.getPlant() != null) {
			poResource.setPlantId(po.getPlant().getId());
		}
		poResource.setPuchaseId(po.getId());
		poResource.setDestination(po.getDestination());
		
		
		Method _cancelPO = null;
		Method _extendPO = null;
		try {
			_cancelPO = PurchaseOrderRESTController.class.getMethod("cancelPO", Long.class);
			_extendPO = PurchaseOrderRESTController.class.getMethod("extendPO", Long.class, InputPurchaseOrderResource.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 

		if(po.getStatus().equals(PurchaseOrderStatuses.ACCEPTED)){
			String cancelPOLink = linkTo(_cancelPO, po.getId()).toUri().toString(); 
			poResource.add(new ExtendedLink(cancelPOLink, "cancelPO", "DELETE"));
		}
		
		PurchaseOrderStatuses[] statusesForExtansion = new
				PurchaseOrderStatuses[]{PurchaseOrderStatuses.ACCEPTED,
				PurchaseOrderStatuses.DELIVERED, PurchaseOrderStatuses.DESPATCHED};
		
	
		if(Arrays.asList(statusesForExtansion).contains(po.getStatus())){
			String extendPOLink = linkTo(_extendPO, po.getId()).toUri().toString(); 
			poResource.add(new ExtendedLink(extendPOLink, "extendPO", "PUT"));
		}
		
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
		invoice.setPrice((Days.daysBetween(new DateTime(po.getStartDate()),
				new DateTime(po.getEndDate())).getDays() + 1) * po.getPlant().getPrice());
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
