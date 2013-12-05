package com.rentit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rentit.security.Users;

@RooJpaRepository(domainType = Users.class)
public interface CustomerRepository {

	@Query("SELECT c FROM Users AS c WHERE c.username = :username)")
	@Transactional(readOnly = true)
	public Users findClientIdByUserName(@Param("username") String username);

}
