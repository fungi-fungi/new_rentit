package com.rentit.repository;
import com.rentit.Customer;
import com.rentit.Plant;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@RooJpaRepository(domainType = Customer.class)
public interface CustomerRepository {
	
	@Query("SELECT c FROM Customer AS c WHERE c.customerId = :id")
	@Transactional(readOnly = true)
	public Customer findCustomerByCustomerId(@Param("id") Long id);
	
	@Query("SELECT c.id FROM Customer AS c WHERE c.customerId = :id")
	@Transactional(readOnly = true)
	public long findIdByCustomerId(@Param("id") Long id);
}
