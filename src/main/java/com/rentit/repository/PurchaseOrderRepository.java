package com.rentit.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;

@RooJpaRepository(domainType = PurchaseOrder.class)
public interface PurchaseOrderRepository {
	
	@Query("SELECT p FROM PurchaseOrder AS p WHERE p.puchaseID = :id")
	@Transactional(readOnly = true)
	PurchaseOrder findPOById(@Param("id") Long id);
	
	@Query("SELECT p FROM PurchaseOrder AS p WHERE p.status = :status")
	@Transactional(readOnly = true)
	List<PurchaseOrder> findPandingPurchaseOrder(@Param("status") PurchaseOrderStatuses status);
}
