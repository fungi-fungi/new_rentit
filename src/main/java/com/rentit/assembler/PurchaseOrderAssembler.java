package com.rentit.assembler;

import java.util.Iterator;
import java.util.List;

import com.renit.rest.PurchaseOrderResource;
import com.renit.rest.PurchaseOrderResourceList;
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
	
	public PurchaseOrderResourceList toResource(List<PurchaseOrder> list) {
		PurchaseOrderResourceList resourcesList = new PurchaseOrderResourceList();

		Iterator<PurchaseOrder> i = list.iterator();
		while (i.hasNext()) {
			resourcesList.addPlant(toResource(i.next()));
		}
		return resourcesList;
	}

}
