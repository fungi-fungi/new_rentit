package com.rentit;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;


@RooJavaBean
@RooJpaActiveRecord
public enum PurchaseOrderStatuses {
	

    ACCEPTED(1, "Accepted"),
    REJECT(1, "Rejected"),
    DESPATCHED(2, "Has beed despatched"),
    DELIVERED(3, "Has been delivered"),
    REJECTED_BY_CUSTOMER(0, "Rejected by customer"),
    INVOICED(5, "Invoice has been send"),
    RETURNED(6, "Plan has been returned"),
    CANCELED(0, "Cenceled by customer");
    
    private final int id;
    
    private final String HRRepresentation;
	
	PurchaseOrderStatuses(int id, String HRRepresentation) {
	    this.id = id;
	    this.HRRepresentation = HRRepresentation;
	}

	
	public int getCode(){
		return this.id;
	}
	
	public String getHRRepresentation(){
		return this.HRRepresentation;
	}
}
