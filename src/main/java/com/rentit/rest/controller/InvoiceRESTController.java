package com.rentit.rest.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rentit.Invoice;
import com.rentit.InvoiceStatuses;
import com.rentit.rest.InvoiceResource;


@Controller
@RequestMapping("/rest/invoices")
public class InvoiceRESTController {
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public ResponseEntity<Void> createInvoice(@RequestBody InvoiceResource invoiceResource){
		
		
		Invoice invoice = new Invoice();
	
		HttpHeaders headers = new HttpHeaders();
		
		try{
			
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
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(invoice.getId().toString()).build().toUri();
			headers.setLocation(location);
			
		}catch(Exception e){
			return new ResponseEntity<Void>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		ResponseEntity<Void> response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return response;
		
	}


}
