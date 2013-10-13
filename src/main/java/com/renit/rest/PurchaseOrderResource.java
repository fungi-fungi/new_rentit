package com.renit.rest;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.Customer;
import com.rentit.Plant;
import com.rentit.Statuses;

@RooJavaBean
@XmlRootElement(name = "po")
public class PurchaseOrderResource {
	/**
	 */
	private Long puchaseID;

	/**
     */
	@Enumerated
	private Statuses status;

	/**
     */
	@OneToOne
	private Customer customer;

	/**
     */
	@OneToOne
	private Plant plant;

	/**
     */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dueDate;

	/**
     */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date startDate;

	/**
     */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date endDate;
}
