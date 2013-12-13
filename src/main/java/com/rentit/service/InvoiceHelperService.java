package com.rentit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentit.Invoice;
import com.rentit.InvoiceStatuses;
import com.rentit.repository.InvoiceRepository;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.rest.InvoiceResource;

@Service
public class InvoiceHelperService {
	
	@Autowired
	InvoiceRepository ioRepository;

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
	
	
	public Invoice addRemitedAdvice(Long id) {

		Invoice invoice = ioRepository.findOne(id);

		invoice.setStatus(InvoiceStatuses.PAID);

		invoice.persist();

		return invoice;
	}

}
