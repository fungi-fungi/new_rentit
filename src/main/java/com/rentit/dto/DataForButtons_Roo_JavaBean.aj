// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.rentit.dto;

import com.rentit.PurchaseOrderStatuses;
import com.rentit.dto.DataForButtons;

privileged aspect DataForButtons_Roo_JavaBean {
    
    public String DataForButtons.getLabel() {
        return this.label;
    }
    
    public void DataForButtons.setLabel(String label) {
        this.label = label;
    }
    
    public PurchaseOrderStatuses DataForButtons.getStatus() {
        return this.status;
    }
    
    public void DataForButtons.setStatus(PurchaseOrderStatuses status) {
        this.status = status;
    }
    
}