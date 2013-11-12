package com.rentit.assembler;

import com.rentit.Invoice;
import com.rentit.soap.InvoiceResource;

public class InvoiceResourceAssembler {

	private final static int MILISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

	public InvoiceResource toResource(Invoice invoice) {

		InvoiceResource invoiceResource = new InvoiceResource();
		invoiceResource.setPurchaseId(invoice.getPurchaseOrder().getId());
		invoiceResource.setDueDate(invoice.getPurchaseOrder().getDueDate());
		invoiceResource.setStartDate(invoice.getPurchaseOrder().getStartDate());
		invoiceResource.setEndDate(invoice.getPurchaseOrder().getEndDate());
		invoiceResource.setPlantName(invoice.getPurchaseOrder().getPlant().getName());
		invoiceResource.setPrice(invoice.getPurchaseOrder().getPlant().getPrice());
		
		invoiceResource.setTotalPrice(
				(long)((invoiceResource.getEndDate().getTime() - invoiceResource.getStartDate().getTime()) / MILISECONDS_IN_DAY)
						* invoice.getPurchaseOrder().getPlant().getPrice());

		return invoiceResource;
	}

}
