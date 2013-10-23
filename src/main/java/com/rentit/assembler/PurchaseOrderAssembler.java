package com.rentit.assembler;

import com.renit.rest.PurchaseOrderResource;
import com.rentit.PurchaseOrder;

public class PurchaseOrderAssembler {

	public PurchaseOrderResource toResource(PurchaseOrder po) {

		PurchaseOrderResource poResource = new PurchaseOrderResource();

		poResource.setStatus(po.getStatus());
		poResource.setStartDate(po.getStartDate());
		poResource.setEndDate(po.getEndDate());
		poResource.setDueDate(po.getDueDate());
		if(po.getPlant() != null) {
			poResource.setPlantId(po.getPlant().getPlantId());
		}
		poResource.setPuchaseID(po.getPuchaseID());
		if(po.getCustomer() != null) {
			poResource.setCustomerId(po.getCustomer().getCustomerId());
		}

		return poResource;
	}

}
