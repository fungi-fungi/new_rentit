package com.rentit.repository;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import com.rentit.Plant;

@RooJpaRepository(domainType = Plant.class)
public interface PlantRepository {

}
