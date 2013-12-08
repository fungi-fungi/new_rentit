package com.rentit.rest;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.dto.DataForButtons;

@RooJavaBean
@XmlRootElement(name = "po")
public class WebPurchaseOrderResource {
	
	private Long puchaseId;
	
	private String planName;
	
	private String customer;
	
	private String destination;
	
	private String currentStatus;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date endDate;
	
	private List<DataForButtons> buttons;
}
