package com.rentit.rest;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.PurchaseOrderStatuses;
import com.rentit.util.ResourceSupport;

@RooJavaBean
@XmlRootElement(name = "po")
public class PurchaseOrderResource extends ResourceSupport {

	private Long puchaseId;

	@Enumerated
	private PurchaseOrderStatuses status;

	@OneToOne
	private long plantId;	

	private String destination;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date endDate;
}
