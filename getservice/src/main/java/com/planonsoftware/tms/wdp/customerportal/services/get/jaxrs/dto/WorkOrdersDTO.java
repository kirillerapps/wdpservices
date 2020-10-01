package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "requestId", "reportedBy", "requestedCompleteOn","reportedOn","modifiedOn","state", "propertyId", "description",
        "inventoryItem", "poNumber", "orderGroup", "internalCoordinator", "externalTradesman" })
public class WorkOrdersDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("requestedCompleteOn")
    private String requestedCompleteOn;
     @JsonProperty("reportedOn")
    private String reportedOn;
    @JsonProperty("modifiedOn")
    private String modifiedOn;    


    @JsonProperty("propertyId")
    private String propertyId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("poNumber")
    private String poNumber;

    @JsonProperty("requestId")
    private String requestId;

    // Nested objects
    @JsonProperty("inventoryItem")
    private WoInventoryItem inventoryItem = new WoInventoryItem();
    @JsonProperty("orderGroup")
    private WoOrderGroup orderGroup = new WoOrderGroup();
    @JsonProperty("internalCoordinator")
    private WoInternalCoordinator internalCoordinator = new WoInternalCoordinator();
    @JsonProperty("externalTradesman")
    private WoExternalTradesman externalTradesman = new WoExternalTradesman();
    @JsonProperty("state")
    private WoState state = new WoState();

    @JsonProperty("reportedBy")
    private ReportedByDTO reportedBy = new ReportedByDTO();

    @JsonProperty("reportedBy")
    public ReportedByDTO getReportedBy() {
        return reportedBy;
    }

    @JsonProperty("reportedBy")
    public void setReportedBy(ReportedByDTO reportedBy) {
        this.reportedBy = reportedBy;
    }

    @JsonProperty("requestId")
    public String getRequestId() {
        return requestId;
    }

    @JsonProperty("requestId")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("requestedCompleteOn")
    public String getRequestedCompleteOn() {
        return requestedCompleteOn;
    }

    @JsonProperty("requestedCompleteOn")
    public void setRequestedCompleteOn(String requestedCompleteOn) {
        this.requestedCompleteOn = requestedCompleteOn;
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
    public String getmodifiedOn() {
        return modifiedOn;
    }

    @JsonProperty("modifiedOn")
    public void setmodifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @JsonProperty("state")
    public WoState getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(WoState state) {
        this.state = state;
    }

    @JsonProperty("propertyId")
    public String getPropertyId() {
        return propertyId;
    }

    @JsonProperty("propertyId")
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("inventoryItem")
    public WoInventoryItem getInventoryItem() {
        return inventoryItem;
    }

    @JsonProperty("inventoryItem")
    public void setInventoryItem(WoInventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    @JsonProperty("poNumber")
    public String getPoNumber() {
        return poNumber;
    }

    @JsonProperty("poNumber")
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    @JsonProperty("orderGroup")
    public WoOrderGroup getOrderGroup() {
        return orderGroup;
    }

    @JsonProperty("orderGroup")
    public void setOrderGroup(WoOrderGroup orderGroup) {
        this.orderGroup = orderGroup;
    }

    @JsonProperty("internalCoordinator")
    public WoInternalCoordinator getInternalCoordinator() {
        return internalCoordinator;
    }

    @JsonProperty("internalCoordinator")
    public void setInternalCoordinator(WoInternalCoordinator internalCoordinator) {
        this.internalCoordinator = internalCoordinator;
    }

    @JsonProperty("externalTradesman")
    public WoExternalTradesman getExternalTradesman() {
        return externalTradesman;
    }

    @JsonProperty("externalTradesman")
    public void setExternalTradesman(WoExternalTradesman externalTradesman) {
        this.externalTradesman = externalTradesman;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "id", "firstName", "lastName" })
    public class WoInternalCoordinator {

        @JsonProperty("id")
        private String id;
        @JsonProperty("firstName")
        private String firstName;
        @JsonProperty("lastName")
        private String lastName;

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("firstName")
        public String getFirstName() {
            return firstName;
        }

        @JsonProperty("firstName")
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @JsonProperty("lastName")
        public String getLastName() {
            return lastName;
        }

        @JsonProperty("lastName")
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "id", "name" })
    public class WoInventoryItem {

        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

    }

 

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "id", "name" })
    public class WoOrderGroup {

        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "id", "name" })
    public class WoState {

        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "id", "name" })
    public class WoExternalTradesman {

        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

    }

}
