package com.rentit.rest;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.InvoiceStatuses;

@RooJavaBean
@XmlRootElement(name = "invoice")
public class WebInvoiceResource {
	
	private long id;
	
	private InvoiceStatuses status;
	
    private float price;
    
    private String plantName;
    
    private Long purchaseOrder;
    
    private String email;
    
    private String clientName;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dueDate;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date startDate;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date endDate;

}
