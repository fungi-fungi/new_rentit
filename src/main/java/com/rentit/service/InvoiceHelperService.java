package com.rentit.service;

import org.springframework.stereotype.Service;

import com.rentit.Invoice;
import com.rentit.InvoiceStatuses;
import com.rentit.rest.InvoiceResource;

@Service
public class InvoiceHelperService {

	public Invoice createInvoice(InvoiceResource invoiceResource) {

		Invoice invoice = new Invoice();

		invoice.setClientName(invoiceResource.getClientName());
		invoice.setDueDate(invoiceResource.getDueDate());
		invoice.setEmail(invoiceResource.getEmail());
		invoice.setEndDate(invoiceResource.getEndDate());
		invoice.setPlantName(invoiceResource.getPlantName());
		invoice.setPrice(invoiceResource.getPrice());
		invoice.setPurchaseOrder(invoiceResource.getPurchaseOrder());
		invoice.setStartDate(invoiceResource.getStartDate());
		invoice.setStatus(InvoiceStatuses.PANDING);

		invoice.persist();

		return invoice;
	}

}
