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

	@Query("SELECT p FROM Plant AS p WHERE p.plantId = :id")
	@Transactional(readOnly = true)
	public Plant findPlantByPlantId(@Param("id") Long id);
	
	@Query("SELECT p.id FROM Plant AS p WHERE p.plantId = :id")
	@Transactional(readOnly = true)
	public long findIdByPlantId(@Param("id") Long id);
	
	@Query("SELECT p FROM Plant AS p WHERE p.plantId NOT IN "
			+ "( SELECT po.plant.plantId FROM PurchaseOrder AS po WHERE po.endDate >= :start AND po.startDate <= :end )")
	@Transactional(readOnly = true)
	public List<Plant> getAvailiblePlant(@Param("start") Date start, @Param("end") Date end);
}
