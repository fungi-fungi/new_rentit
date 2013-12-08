package com.rentit.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.exception.InvalidHirePeriodException;
import com.rentit.exception.NotAcceptableException;
import com.rentit.exception.ResourceNotFoundException;
import com.rentit.exception.PlantUnavailableException;
import com.rentit.repository.CustomerRepository;
import com.rentit.repository.PlantRepository;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.rest.InputPurchaseOrderResource;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository poRepository;
	@Autowired
	PlantRepository plantRepository;
	@Autowired
	CustomerRepository customerRepository;

	public PurchaseOrder createPO(InputPurchaseOrderResource poResource)
			throws PlantUnavailableException, InvalidHirePeriodException {

		String user = ((User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();
		PurchaseOrder po = new PurchaseOrder();

		if (plantRepository.findIfPlantAvaliable(poResource.getPlantId(),
				poResource.getStartDate(), poResource.getEndDate()) == null) {
			throw new PlantUnavailableException("Plant not availible");
		}

		if (poResource.getEndDate().after(poResource.getStartDate())
				&& poResource.getStartDate().after(new Date())) {

			po.setCustomer(customerRepository.findClientIdByUserName(user));
			po.setPlant(plantRepository.findOne(poResource.getPlantId()));
			po.setStatus(PurchaseOrderStatuses.ACCEPTED);
			po.setStartDate(poResource.getStartDate());
			po.setEndDate(poResource.getEndDate());
			po.setDestination(poResource.getDestination());
			po.persist();
		} else {
			throw new InvalidHirePeriodException("Wrong date period");
		}

		return po;

	}

	public PurchaseOrder extendPO(InputPurchaseOrderResource poResource, Long id)
			throws PlantUnavailableException, InvalidHirePeriodException,
			ResourceNotFoundException {

		String user = ((User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();

		PurchaseOrder po = poRepository.findPOSByIdForUser(id, user);

		if (po == null) {
			throw new ResourceNotFoundException("Resource not found");
		}

		// Get next day after End Date
		DateTime DayAfterEndDate = new DateTime(po.getEndDate()).plusDays(1);

		// Check if plant is available for requested period
		if (plantRepository.findIfPlantAvaliable(poResource.getPlantId(),
				DayAfterEndDate.toDate(), poResource.getEndDate()) == null) {
			throw new PlantUnavailableException(
					"Plant is unavalible for given period");
		}

		if (poResource.getEndDate().after(po.getEndDate())) {
			po.setEndDate(poResource.getEndDate());
			po.persist();
		} else {
			throw new InvalidHirePeriodException("Wrong period given");
		}

		return po;

	}

	public PurchaseOrder cancelPO(Long id) throws ResourceNotFoundException,
			NotAcceptableException {

		String user = ((User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();

		PurchaseOrder po = poRepository.findPOSByIdForUser(id, user);
		if (po == null) {
			throw new ResourceNotFoundException("Resource not found");
		}

		if (po.getStartDate().after(new Date())
				&& (Days.daysBetween(new DateTime(po.getStartDate()),
						new DateTime()).getDays() > 1)) {
			po.setStatus(PurchaseOrderStatuses.CANCELED);
			po.persist();
		} else {
			throw new NotAcceptableException("Plant has been already dispatched");
		}

		return po;

	}

	public PurchaseOrder getPO(Long id) throws ResourceNotFoundException {

		String user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		PurchaseOrder po = poRepository.findPOSByIdForUser(id, user);
		if (po == null) {
			throw new ResourceNotFoundException("Resource not found");
		}

		return po;
	}

	public List<PurchaseOrder> getAllPO() {

		String user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		List<PurchaseOrder> po = poRepository.findPOSForUser(user);

		return po;
	}

}
