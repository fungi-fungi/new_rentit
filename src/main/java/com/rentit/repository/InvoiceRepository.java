package com.rentit.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rentit.Invoice;
import com.rentit.InvoiceStatuses;

@RooJpaRepository(domainType = Invoice.class)
public interface InvoiceRepository {
	
	@Query("SELECT i FROM Invoice AS i WHERE i.status = :status)")
	@Transactional(readOnly = true)
	public List<Invoice> findByStatus(@Param("status") InvoiceStatuses status);
}
