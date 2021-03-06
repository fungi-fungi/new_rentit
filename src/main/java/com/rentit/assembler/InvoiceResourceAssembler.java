package com.rentit.assembler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rentit.Invoice;
import com.rentit.rest.InvoiceResource;
import com.rentit.rest.InvoiceToSendResource;
import com.rentit.rest.WebInvoiceResource;

public class InvoiceResourceAssembler {

	public InvoiceResource toResource(Invoice invoice) {

		InvoiceResource invoiceResource = new InvoiceResource();
		
		invoiceResource.setClientName(invoice.getClientName());
		invoiceResource.setDueDate(invoice.getDueDate());
		invoiceResource.setEmail(invoice.getEmail());
		invoiceResource.setEndDate(invoice.getEndDate());
		invoiceResource.setPlantName(invoice.getPlantName());
		invoiceResource.setPrice(invoice.getPrice());
		invoiceResource.setPurchaseOrder(invoice.getPurchaseOrder());
		invoiceResource.setStartDate(invoice.getStartDate());
		
		return invoiceResource;
	}
	
	
	public InvoiceToSendResource toEmailResource(Invoice invoice) {

		InvoiceToSendResource invoiceResource = new InvoiceToSendResource();
		
		invoiceResource.setInvoiceId(invoice.getId());
		invoiceResource.setDueDate(invoice.getDueDate());
		invoiceResource.setEndDate(invoice.getEndDate());
		invoiceResource.setPlantName(invoice.getPlantName());
		invoiceResource.setPrice(invoice.getPrice());
		invoiceResource.setPurchaseOrder(invoice.getPurchaseOrder());
		invoiceResource.setStartDate(invoice.getStartDate());
		
		return invoiceResource;
	}
	
	
	public WebInvoiceResource toWebResource(Invoice invoice) {

		WebInvoiceResource invoiceResource = new WebInvoiceResource();
		
		invoiceResource.setStatus(invoice.getStatus());
		invoiceResource.setId(invoice.getId());
		invoiceResource.setClientName(invoice.getClientName());
		invoiceResource.setDueDate(invoice.getDueDate());
		invoiceResource.setEmail(invoice.getEmail());
		invoiceResource.setEndDate(invoice.getEndDate());
		invoiceResource.setPlantName(invoice.getPlantName());
		invoiceResource.setPrice(invoice.getPrice());
		invoiceResource.setPurchaseOrder(invoice.getPurchaseOrder());
		invoiceResource.setStartDate(invoice.getStartDate());
		
		return invoiceResource;
	}
	
	public List<WebInvoiceResource> toWebResource(List<Invoice> list) {
		List<WebInvoiceResource> resourcesList = new ArrayList<WebInvoiceResource>();

		Iterator<Invoice> i = list.iterator();
		while (i.hasNext()) {
			resourcesList.add(toWebResource(i.next()));
		}
		return resourcesList;
	}

}
