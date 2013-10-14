package com.rentit.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renit.rest.PurchaseOrderResource;
import com.rentit.PurchaseOrder;
import com.rentit.Statuses;

@Controller
@RequestMapping("/rest/po")
public class PurchaseOrderRESTController {
	
	@Autowired com.rentit.repository.PurchaseOrderRepository poRepository;
	@RequestMapping(method=RequestMethod.DELETE, value="{id}/accept")
	public ResponseEntity<Void> cencelPurchaseOrderResource(@PathVariable Long id) {
		PurchaseOrder po = poRepository.findOne(id);
		po.setStatus(Statuses.CANCELED);
		po.persist();
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="{id}")
	public ResponseEntity<Void> modifyPurchaseOrderResource(@PathVariable Long id, @RequestBody PurchaseOrderResource poResource) {
		
		PurchaseOrder po = poRepository.findOne(id);
		po.setPuchaseID(poResource.getPuchaseID());
		po.setCustomer(poResource.getCustomer());
		po.setPlant(poResource.getPlant());
		// New request should be panding
		po.setStatus(poResource.getStatus());
		po.setStartDate(poResource.getStartDate());
		po.setEndDate(poResource.getEndDate());
		po.setDueDate(poResource.getDueDate());
		po.persist();

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public ResponseEntity<Void> createPurchaseOrderResource(@RequestBody PurchaseOrderResource poResource) {
		
		PurchaseOrder po = new PurchaseOrder();
		po.setPuchaseID(poResource.getPuchaseID());
		po.setCustomer(poResource.getCustomer());
		po.setPlant(poResource.getPlant());
		// New request should be panding
		po.setStatus(Statuses.PANDING);
		po.setStartDate(poResource.getStartDate());
		po.setEndDate(poResource.getEndDate());
		po.setDueDate(poResource.getDueDate());
		po.persist();

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}

}
