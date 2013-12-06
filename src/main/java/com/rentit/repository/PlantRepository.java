package com.rentit.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rentit.Plant;

@RooJpaRepository(domainType = Plant.class)
public interface PlantRepository {

	//TODO: Fix query to filter by Status as well
	
	@Query("SELECT p FROM Plant AS p WHERE p.id NOT IN "
			+ "( SELECT po.plant.id FROM PurchaseOrder AS po "
			+ "WHERE po.endDate >= :start AND po.startDate <= :end)")
	@Transactional(readOnly = true)
	public List<Plant> findAvailiblePlants(@Param("start") Date start, @Param("end") Date end);
	
	
	@Query("SELECT p FROM Plant AS p WHERE p.id = :id AND :id NOT IN "
			+ "( SELECT po.plant.id FROM PurchaseOrder AS po "
			+ "WHERE po.endDate >= :start AND po.startDate <= :end)")
	
	@Transactional(readOnly = true)
	public Plant findIfPlantAvaliable(@Param("id") Long id, @Param("start") Date start, @Param("end") Date end);
}
