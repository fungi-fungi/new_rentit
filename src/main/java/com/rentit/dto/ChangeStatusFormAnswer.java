package com.rentit.dto;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.rentit.PurchaseOrderStatuses;


@RooJavaBean
public class ChangeStatusFormAnswer {

	Long purchaseOrderId;
	
	PurchaseOrderStatuses status;
	
}
