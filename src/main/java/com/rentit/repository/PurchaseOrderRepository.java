package com.rentit.repository;
import java.util.Date;
import java.util.List;

import com.rentit.PurchaseOrder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@RooJpaRepository(domainType = PurchaseOrder.class)
public interface PurchaseOrderRepository {
	
	/*@Query("SELECT PurchaseOrder FROM PurchaseOrder WHERE PurchaseOrder.puchaseID = :id")
	@Transactional(readOnly = true)
	PurchaseOrder findPOById(@Param("id") Long id);*/
}
