package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

import java.util.Date;

public class FilterDTO
{
    private String notModifiedBy;
    private String modifiedBy;
    private Date modifiedDateFrom;
    private Date modifiedDateTo;



    public String getNotModifiedBy() {
        return notModifiedBy;
    }

    public void setNotModifiedBy(String notModifiedBy) {
        this.notModifiedBy = notModifiedBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDateFrom() {
        return modifiedDateFrom;
    }
    public void setModifiedDateFrom(Date modifiedDateFrom) {
        this.modifiedDateFrom = modifiedDateFrom;
    }
    public Date getModifiedDateTo() {
        return modifiedDateTo;
    }
    public void setModifiedDateTo(Date modifiedDateTo) {
        this.modifiedDateTo = modifiedDateTo;
    }
}
