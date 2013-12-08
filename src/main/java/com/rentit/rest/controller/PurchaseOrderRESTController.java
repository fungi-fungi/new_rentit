package com.rentit.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renit.rest.InputPurchaseOrderResource;
import com.renit.rest.PurchaseOrderResource;
import com.renit.rest.PurchaseOrderResourceCollection;
import com.rentit.PurchaseOrder;
import com.rentit.assembler.PurchaseOrderAssembler;
import com.rentit.exception.InvalidHirePeriodException;
import com.rentit.exception.NotAcceptableException;
import com.rentit.exception.ResourceNotFoundException;
import com.rentit.exception.PlantUnavailableException;
import com.rentit.service.PurchaseOrderService;

@Controller
@RequestMapping("/rest/pos")
public class PurchaseOrderRESTController {

	@Autowired
	PurchaseOrderService poService;
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PurchaseOrderResourceCollection> getPurchaseOrders() {	
	
		List<PurchaseOrder> po = poService.getAllPO();
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		
		return new ResponseEntity<PurchaseOrderResourceCollection>(assembler.toResource(po), new HttpHeaders(), HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PurchaseOrderResource> getPurchaseOrder(@PathVariable Long id) throws NoSuchMethodException, SecurityException, ResourceNotFoundException {	
		
		PurchaseOrder po = poService.getPO(id);
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		PurchaseOrderResource resource = assembler.toResource(po);
		
		return new ResponseEntity<PurchaseOrderResource>(resource, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}/cancel")
	public ResponseEntity<Void> cancelPO(@PathVariable Long id) throws ResourceNotFoundException, NotAcceptableException {
		
		PurchaseOrder po = poService.cancelPO(id);
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();
		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<PurchaseOrderResource> extendPO(
			@PathVariable Long id,
			@RequestBody InputPurchaseOrderResource poResource) throws PlantUnavailableException, InvalidHirePeriodException, ResourceNotFoundException {
		
		PurchaseOrder po = poService.extendPO(poResource, id);
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<PurchaseOrderResource> response = new ResponseEntity<PurchaseOrderResource>(assembler.toResource(po), headers, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public ResponseEntity<PurchaseOrderResource> createPurchaseOrderResource(
			@RequestBody InputPurchaseOrderResource poResource) throws PlantUnavailableException, InvalidHirePeriodException {
		
		
		PurchaseOrder po = poService.createPO(poResource);		
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<PurchaseOrderResource> response = new ResponseEntity<PurchaseOrderResource>(assembler.toResource(po), headers, HttpStatus.CREATED);
		return response;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Void> handleResoiurceNotFound(ResourceNotFoundException ex) {

		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidHirePeriodException.class)
	public ResponseEntity<Void> handleInvalidDatePeriod(ResourceNotFoundException ex) {

		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotAcceptableException.class)
	public ResponseEntity<Void> handleNotAcceptable(NotAcceptableException ex) {

		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(PlantUnavailableException.class)
	public ResponseEntity<Void> handlePlantUnavailable(PlantUnavailableException ex) {

		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}

}
