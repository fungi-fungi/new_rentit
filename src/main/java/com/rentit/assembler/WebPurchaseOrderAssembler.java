package com.rentit.assembler;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.renit.rest.WebPurchaseOrderResource;
import com.renit.rest.WebPurchaseOrderResourceCollection;
import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.dto.DataForButtons;

public class WebPurchaseOrderAssembler {
	
	final static private String LABEL_FOR_DESPATCH = "Mark as despatched"; 
	final static private String LABEL_FOR_DELIVERED = "Mark as delivered"; 
	final static private String LABEL_FOR_REJECTED_BY_CLIENT = "Mark as rejected by user"; 
	final static private String LABEL_FOR_RETURNED = "Mark as returned";
	
	public WebPurchaseOrderResource toResource(PurchaseOrder po) {

		WebPurchaseOrderResource poResource = new WebPurchaseOrderResource();

		poResource.setEndDate(po.getEndDate());
		poResource.setDestination(po.getDestination());
		poResource.setStartDate(po.getStartDate());
		poResource.setCurrentStatus(po.getStatus().getHRRepresentation());
		if(po.getPlant() != null) {
			poResource.setPlanName(po.getPlant().getName());
		}
		poResource.setPuchaseId(po.getId());
		if(po.getCustomer() != null) {
			poResource.setCustomer(po.getCustomer().getUsername());
		}
		
		if(po.getStatus().equals(PurchaseOrderStatuses.ACCEPTED)){
			DataForButtons button =  new DataForButtons();
			button.setLabel(LABEL_FOR_DESPATCH);
			button.setStatus(PurchaseOrderStatuses.DESPATCHED);
			List<DataForButtons> listOfButtons = new ArrayList<DataForButtons>();
			listOfButtons.add(button);
			poResource.setButtons(listOfButtons);
		}
		
		if(po.getStatus().equals(PurchaseOrderStatuses.DESPATCHED)){
			
			DataForButtons button1 =  new DataForButtons();
			button1.setLabel(LABEL_FOR_DELIVERED);
			button1.setStatus(PurchaseOrderStatuses.DELIVERED);
			
			DataForButtons button2 =  new DataForButtons();
			button2.setLabel(LABEL_FOR_REJECTED_BY_CLIENT);
			button2.setStatus(PurchaseOrderStatuses.REJECTED_BY_CUSTOMER);
			
			List<DataForButtons> listOfButtons = new ArrayList<DataForButtons>();
			listOfButtons.add(button1);
			listOfButtons.add(button2);
			poResource.setButtons(listOfButtons);
		}
		
		if(po.getStatus().equals(PurchaseOrderStatuses.DELIVERED)){
			DataForButtons button =  new DataForButtons();
			button.setLabel(LABEL_FOR_RETURNED);
			button.setStatus(PurchaseOrderStatuses.RETURNED);
			List<DataForButtons> listOfButtons = new ArrayList<DataForButtons>();
			listOfButtons.add(button);
			poResource.setButtons(listOfButtons);
		}

		return poResource;
	}
	
	public List<WebPurchaseOrderResource> toListResource(List<PurchaseOrder> list) {
		List<WebPurchaseOrderResource> resourcesList = new ArrayList<WebPurchaseOrderResource>();

		Iterator<PurchaseOrder> i = list.iterator();
		while (i.hasNext()) {
			resourcesList.add(toResource(i.next()));
		}
		return resourcesList;
	}
	
	public WebPurchaseOrderResourceCollection toResource(List<PurchaseOrder> list) {
		WebPurchaseOrderResourceCollection resourcesList = new WebPurchaseOrderResourceCollection();

		Iterator<PurchaseOrder> i = list.iterator();
		while (i.hasNext()) {
			resourcesList.addPlant(toResource(i.next()));
		}
		return resourcesList;
	}

}
