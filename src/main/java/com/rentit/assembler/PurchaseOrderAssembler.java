package com.rentit.assembler;

import com.renit.rest.PurchaseOrderResource;
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
		poResource.setPuchaseId(po.getPuchaseId());
		if(po.getCustomer() != null) {
			poResource.setCustomerId(po.getCustomer().getId());
		}
		poResource.setDestination(po.getDestination());

		return poResource;
	}

}
