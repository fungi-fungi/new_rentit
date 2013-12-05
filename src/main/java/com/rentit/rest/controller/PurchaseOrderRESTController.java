package com.rentit.rest.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
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

import com.renit.rest.InputPurchaseOrderResource;
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
		
		String user =  ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		PurchaseOrder po = poRepository.getPOSByIdForUser(id, user);
		if(po == null){
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}

		if(po.getStartDate().after(new Date()) && (Days.daysBetween(new DateTime(po.getStartDate()), new DateTime()).getDays() > 1 )){
			po.setStatus(PurchaseOrderStatuses.CANCELED);
			po.persist();
		}else{
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_ACCEPTABLE);
		}
		
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();
		headers.setLocation(location);
		
		//TODO: Check status
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<PurchaseOrderResource> modifyPurchaseOrderResource(
			@PathVariable Long id,
			@RequestBody InputPurchaseOrderResource poResource) {
		
		String user =  ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		
		PurchaseOrder po = poRepository.getPOSByIdForUser(id, user);
		
		if(po == null){
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
		
		// Get next day after End Date
		DateTime DayAfterEndDate = new DateTime(po.getEndDate()).plusDays(1);
		
		// Check if plant is available for requested period
		if(plantRepository.getIfPlantAvaliable(poResource.getPlantId(), DayAfterEndDate.toDate(), poResource.getEndDate()) == null){
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(poResource.getEndDate().after(po.getEndDate())){
			po.setEndDate(poResource.getEndDate());
			po.persist();
		}else{
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<PurchaseOrderResource> response = new ResponseEntity<PurchaseOrderResource>(assembler.toResource(po), headers, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public ResponseEntity<PurchaseOrderResource> createPurchaseOrderResource(
			@RequestBody InputPurchaseOrderResource poResource) {
		
		String user =  ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
		PurchaseOrder po = new PurchaseOrder();
		
		if(plantRepository.getIfPlantAvaliable(poResource.getPlantId(), poResource.getStartDate(), poResource.getEndDate()) == null){
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(poResource.getEndDate().after(poResource.getStartDate()) && poResource.getStartDate().after(new Date())){
			
			po.setCustomer(customerRepository.findClientIdByUserName(user));
			po.setPlant(plantRepository.findOne(poResource.getPlantId()));
			po.setStatus(PurchaseOrderStatuses.ACCEPTED);
			po.setStartDate(poResource.getStartDate());
			po.setEndDate(poResource.getEndDate());
			po.setDestination(poResource.getDestination());
			po.persist();
		}else{
			return new ResponseEntity<>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(po.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<PurchaseOrderResource> response = new ResponseEntity<PurchaseOrderResource>(assembler.toResource(po), headers, HttpStatus.CREATED);
		return response;
	}

}
