package com.rentit;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;


@RooJavaBean
@RooJpaActiveRecord
public enum PurchaseOrderStatuses {
	

    ACCEPTED(1),
    DESPATCHED(2),
    DELIVERED(3),
    REJECTED_BY_CUSTOMER(0),
    INVOICED(5),
    CANCELED(0);
    
    private final int id;
	
	PurchaseOrderStatuses(int id) {
	    this.id = id;
	}
	
	public int getCode(){
		return this.id;
	}
}
