package com.rentit.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renit.rest.PurchaseOrderResource;
import com.renit.rest.PurchaseOrderResourceList;
import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.assembler.PurchaseOrderAssembler;
import com.rentit.repository.CustomerRepository;
import com.rentit.repository.PlantRepository;
import com.rentit.repository.PurchaseOrderRepository;

@Controller
@RequestMapping("/rest/pos")
public class PurchaseOrderRESTController {
	
	@Autowired 
	PurchaseOrderRepository poRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	PlantRepository plantRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PurchaseOrderResourceList> getPurchaseOrders() {	
		
		
		String user =  ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		List<PurchaseOrder> po = poRepository.getPOSForUser(user);
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		
		return new ResponseEntity<PurchaseOrderResourceList>(assembler.toResource(po), new HttpHeaders(), HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PurchaseOrderResource> getPurchaseOrder(@PathVariable Long id) {	
		
		String user =  ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		PurchaseOrder po = poRepository.getPOSByIdForUser(id, user);
		
		if(po == null){
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
		
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		
		return new ResponseEntity<PurchaseOrderResource>(
				assembler.toResource(po), new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}/cancel")
	public ResponseEntity<Void> cencelPurchaseOrderResource(@PathVariable Long id) {
		
		PurchaseOrder po = poRepository.findOne(id);
		po.setStatus(PurchaseOrderStatuses.CANCELED);
		po.persist();
		
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();
		headers.setLocation(location);
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public ResponseEntity<Void> modifyPurchaseOrderResource(@PathVariable Long id, @RequestBody PurchaseOrderResource poResource) {
		
		PurchaseOrder po = poRepository.findOne(id);
		po.setId(poResource.getPuchaseId());
		
		//TODO: Check if plant and customer exist
		po.setCustomer(customerRepository.findOne(poResource.getCustomerId()));
		po.setPlant(plantRepository.findOne(poResource.getPlantId()));
		po.setStatus(poResource.getStatus());
		po.setStartDate(poResource.getStartDate());
		po.setEndDate(poResource.getEndDate());
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
		po.setId(poResource.getPuchaseId());
		//TODO: Check if plant and customer exist
		po.setCustomer(customerRepository.findOne(poResource.getCustomerId()));
		po.setPlant(plantRepository.findOne(poResource.getPlantId()));
		// New request should be panding
		po.setStatus(PurchaseOrderStatuses.PANDING);
		po.setStartDate(poResource.getStartDate());
		po.setEndDate(poResource.getEndDate());
		po.persist();

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}

}
