package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

public class FilterDTO
{
    private String description;
    private String state;
    private String reportedById;
    private String modifiedDateFrom;
    private String modifiedDateTo;

  public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getReportedById() {
        return reportedById;
    }
    public void setReportedById(String reportedById) {
        this.reportedById = reportedById;
    }
    public String getModifiedDateFrom() {
        return modifiedDateFrom;
    }
    public void setModifiedDateFrom(String modifiedDateFrom) {
        this.modifiedDateFrom = modifiedDateFrom;
    }
    public String getModifiedDateTo() {
        return modifiedDateTo;
    }
    public void setModifiedDateTo(String modifiedDateTo) {
        this.modifiedDateTo = modifiedDateTo;
    }
}
