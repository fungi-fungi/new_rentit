package com.rentit.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentit.Plant;
import com.rentit.exception.InvalidHirePeriodException;
import com.rentit.exception.PlantUnavailableException;
import com.rentit.repository.PlantRepository;

@Service
public class PlantHelperService {

	@Autowired
	PlantRepository plantRepository;

	public List<Plant> getAllAvaliblePlants(String start, String end)
			throws InvalidHirePeriodException {

		List<Plant> plants = new ArrayList<Plant>();

		if (end != null && start != null) {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			try {

				Date startDate = dateFormat.parse(start);
				Date endDate = dateFormat.parse(end);

				if (endDate.after(startDate) && startDate.after(new Date())) {
					plants = plantRepository.findAvailiblePlants(startDate,
							endDate);
				} else {
					throw new InvalidHirePeriodException("Wrong date format or interval");
				}

			} catch (ParseException e) {
				throw new InvalidHirePeriodException("No date format provided");
			}

		} else if (end == null && start == null) {
			plants = plantRepository.findAll();
		} else {
			throw new InvalidHirePeriodException("Wrong date format or interval");
		}

		return plants;

	}

	public Plant getPlantIfAvaliable(Long id, String start, String end)
			throws PlantUnavailableException, InvalidHirePeriodException {

		Plant plant = plantRepository.findOne(id);

		if (plant == null) {
			throw new PlantUnavailableException("Resource not found");
		} else {

			if (end != null && start != null) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				try {

					Date startDate = dateFormat.parse(start);
					Date endDate = dateFormat.parse(end);

					if (endDate.after(startDate)) {
						plant = plantRepository.findIfPlantAvaliable(id, startDate, endDate);
					} else {
						throw new InvalidHirePeriodException("Wrong date interval");
					}
				} catch (ParseException e) {
					throw new InvalidHirePeriodException("Wrong date format or interval");
				}

			} else if (end != null || start != null) {
				throw new InvalidHirePeriodException("Wrong date format or interval");
			}
		}

		return plant;
	}
}
