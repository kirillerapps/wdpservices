package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "reportedOn", "modifiedOn", "requestId", "fileName", "base64Data"})

public class ComlogDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("reportedOn")
    private String reportedOn;
    @JsonProperty("modifiedOn")
    private String modifiedOn;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("fileName")
    private String fileName;
    @JsonProperty("base64Data")
    private String base64Data;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("reportedOn")
    public String getReportedOn() {
        return reportedOn;
    }

    @JsonProperty("reportedOn")
    public void setReportedOn(String reportedOn) {
        this.reportedOn = reportedOn;
    }

    @JsonProperty("modifiedOn")
    public String getModifiedOn() {
        return modifiedOn;
    }

    @JsonProperty("modifiedOn")
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @JsonProperty("requestId")
    public String getRequestId() {
        return requestId;
    }

    @JsonProperty("requestId")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("fileName")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @JsonProperty("base64Data")
    public String getBase64Data() {
        return base64Data;
    }

    @JsonProperty("base64Data")
    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    // @JsonProperty("modifiedBy")
    // public String getmodifiedBy() {
    // return modifiedBy;
    // }

    // @JsonProperty("modifiedBy")
    // public void setmodifiedBy(String modifiedBy) {
    // this.modifiedBy = modifiedBy;
    // }

}
