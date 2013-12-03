package com.rentit.assembler;

import com.renit.rest.CustomerResource;
import com.rentit.Customer;

public class CustomerResourceAssembler {
	
	public CustomerResource toResource(Customer customer) {

		CustomerResource customerResource = new CustomerResource();
		customerResource.setName(customer.getName());
		customerResource.setCustomerId(customer.getId());
		customerResource.setEmail(customer.getEmail());
		
		return customerResource;
	}

}
