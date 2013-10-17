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

import com.renit.rest.CustomerResource;
import com.rentit.Customer;
import com.rentit.assembler.CustomerResourceAssembler;

@Controller
@RequestMapping("/rest/customer")
public class CustomerRESTController {
	
	@Autowired
	com.rentit.repository.CustomerRepository customerRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<CustomerResource> getPlantResource(@PathVariable Long id) {

		Customer customer = customerRepository.findCustomerByCustomerId(id);
		CustomerResourceAssembler assembler = new CustomerResourceAssembler();
		
		return new ResponseEntity<CustomerResource>(
				assembler.toResource(customer), new HttpHeaders(),	HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<Void> incertPlantResource(@RequestBody CustomerResource customerResource) {
		
		Customer customer = new Customer();
		customer.setName(customerResource.getName());
		customer.setCustomerId(customerResource.getCustomerId());
		customer.persist();
		
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(customer.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deletePlantResource(@PathVariable Long id) {

		
		Customer customer = customerRepository.findCustomerByCustomerId(id);
		
		customerRepository.delete(customer);

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(customer.getId().toString()).build().toUri();

		headers.setLocation(location);
		
		ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}

}
