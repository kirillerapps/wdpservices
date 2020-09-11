package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

public class OrderDetailsDTO
{
    private String orderNumber;
    private String requestedBy;
    private String requestedFor;
    private String property;
    private String description;

    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getRequestedBy() {
        return requestedBy;
    }
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
    public String getRequestedFor() {
        return requestedFor;
    }
    public void setRequestedFor(String requestedFor) {
        this.requestedFor = requestedFor;
    }
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
   
}
