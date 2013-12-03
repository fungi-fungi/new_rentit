package com.rentit.soap;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rentit.PurchaseOrder;

public class WebPurchaseOrderAssembler {
	
	public WebPurchaseOrderResource toResource(PurchaseOrder po) {

		WebPurchaseOrderResource poResource = new WebPurchaseOrderResource();

		poResource.setStatus(po.getStatus());
		poResource.setStartDate(po.getStartDate());
		poResource.setEndDate(po.getEndDate());
		if(po.getPlant() != null) {
			poResource.setPlanName(po.getPlant().getName());
		}
		poResource.setPuchaseID(po.getPuchaseId());
		if(po.getCustomer() != null) {
			poResource.setCustomer(po.getCustomer().getName());
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
