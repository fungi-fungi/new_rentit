package com.rentit.dto;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.PurchaseOrderStatuses;


@RooJavaBean
public class DataForButtons {
	
	String label;
	
	PurchaseOrderStatuses status;

}
