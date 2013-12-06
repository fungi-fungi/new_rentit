package com.rentit.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;

@RooJpaRepository(domainType = PurchaseOrder.class)
public interface PurchaseOrderRepository {
	
	@Query("SELECT p FROM PurchaseOrder AS p WHERE p.status = :status")
	@Transactional(readOnly = true)
	List<PurchaseOrder> findPandingPurchaseOrder(@Param("status") PurchaseOrderStatuses status);
	
	@Query("SELECT po FROM PurchaseOrder AS po WHERE po.id = :id AND po.customer.username = :username )")
	@Transactional(readOnly = true)
	PurchaseOrder findPOSByIdForUser(@Param("id") Long id, @Param("username") String username);
	
	@Query("SELECT po FROM PurchaseOrder AS po WHERE po.customer.username = :username )")
	@Transactional(readOnly = true)
	List<PurchaseOrder> findPOSForUser(@Param("username") String username);

	@Query("SELECT po FROM PurchaseOrder AS po WHERE po.startDate = :date )")
	@Transactional(readOnly = true)
	List<PurchaseOrder> findPOSByDate(@Param("date") Date date);

}



