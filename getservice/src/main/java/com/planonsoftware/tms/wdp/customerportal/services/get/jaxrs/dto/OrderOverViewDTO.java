package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

public class OrderOverViewDTO
{   
    private String code;   
    private String description;
    private String reportedOn;
    private String status; 

    public void setDescription(String description) {
        this.description = description;
    }
       public String getDescription() {
        return description;
    }
    public String getReportedOn() {
        return reportedOn;
    }
    public void setReportedOn(String reportedOn) {
        this.reportedOn = reportedOn;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }
    public void setCode(final String code) {
        this.code = code;
    }



}
