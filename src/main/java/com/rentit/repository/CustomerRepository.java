package com.rentit.repository;
import com.rentit.security.Users;
import com.rentit.Plant;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@RooJpaRepository(domainType = Users.class)
public interface CustomerRepository {

	
}
