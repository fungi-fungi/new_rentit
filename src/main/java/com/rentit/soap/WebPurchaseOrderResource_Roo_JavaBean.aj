// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.rentit.soap;

import com.rentit.Statuses;
import com.rentit.soap.WebPurchaseOrderResource;
import java.util.Date;

privileged aspect WebPurchaseOrderResource_Roo_JavaBean {
    
    public Long WebPurchaseOrderResource.getPuchaseID() {
        return this.puchaseID;
    }
    
    public void WebPurchaseOrderResource.setPuchaseID(Long puchaseID) {
        this.puchaseID = puchaseID;
    }
    
    public String WebPurchaseOrderResource.getPlanName() {
        return this.planName;
    }
    
    public void WebPurchaseOrderResource.setPlanName(String planName) {
        this.planName = planName;
    }
    
    public String WebPurchaseOrderResource.getCustomer() {
        return this.customer;
    }
    
    public void WebPurchaseOrderResource.setCustomer(String customer) {
        this.customer = customer;
    }
    
    public Statuses WebPurchaseOrderResource.getStatus() {
        return this.status;
    }
    
    public void WebPurchaseOrderResource.setStatus(Statuses status) {
        this.status = status;
    }
    
    public Date WebPurchaseOrderResource.getDueDate() {
        return this.dueDate;
    }
    
    public void WebPurchaseOrderResource.setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public Date WebPurchaseOrderResource.getStartDate() {
        return this.startDate;
    }
    
    public void WebPurchaseOrderResource.setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date WebPurchaseOrderResource.getEndDate() {
        return this.endDate;
    }
    
    public void WebPurchaseOrderResource.setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
