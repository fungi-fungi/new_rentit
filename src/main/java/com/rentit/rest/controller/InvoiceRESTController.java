package com.rentit.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rentit.Invoice;
import com.rentit.rest.InvoiceResource;
import com.rentit.service.InvoiceHelperService;

@Controller
@RequestMapping("/rest/invoices")
public class InvoiceRESTController {

	@Autowired
	InvoiceHelperService invoiceService;

	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<Void> createInvoice(@RequestBody InvoiceResource invoiceResource) {

		Invoice invoice = invoiceService.createInvoice(invoiceResource);

		HttpHeaders headers = new HttpHeaders();

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.pathSegment(invoice.getId().toString()).build().toUri();
		headers.setLocation(location);

		ResponseEntity<Void> response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return response;

	}

}
