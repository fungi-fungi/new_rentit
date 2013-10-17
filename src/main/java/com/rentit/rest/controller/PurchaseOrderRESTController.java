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
import com.rentit.Plant;
import com.rentit.PurchaseOrder;
import com.rentit.Statuses;
import com.rentit.assembler.PurchaseOrderAssembler;
import com.rentit.repository.CustomerRepository;
import com.rentit.repository.PlantRepository;
import com.rentit.repository.PurchaseOrderRepository;

@Controller
@RequestMapping("/rest/po")
public class PurchaseOrderRESTController {
	
	@Autowired 
	PurchaseOrderRepository poRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	PlantRepository plantRepository;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<PurchaseOrderResource> getPurchaseOrder(@PathVariable Long id) {

		PurchaseOrder po = poRepository.findPOById(id);
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		
		return new ResponseEntity<PurchaseOrderResource>(
				assembler.toResource(po), new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {

		PurchaseOrder po = poRepository.findPOById(id);		
		poRepository.delete(po);

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();
		headers.setLocation(location);
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="{id}/accept")
	public ResponseEntity<Void> cencelPurchaseOrderResource(@PathVariable Long id) {
		PurchaseOrder po = poRepository.findPOById(id);
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
		
		PurchaseOrder po = poRepository.findPOById(id);
		po.setPuchaseID(poResource.getPuchaseID());
		
		//TODO: Check if plant and customer exist
		po.setCustomer(customerRepository.findCustomerByCustomerId(poResource.getCustomerId()));
		po.setPlant(plantRepository.findPlantByPlantId(poResource.getPlantId()));
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
		//TODO: Check if plant and customer exist
		po.setCustomer(customerRepository.findCustomerByCustomerId(poResource.getCustomerId()));
		po.setPlant(plantRepository.findPlantByPlantId(poResource.getPlantId()));
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
