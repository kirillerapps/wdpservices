package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@JsonPropertyOrder({
        "id","standardOrderId", "description", "propertyId", "reportedBy", "uploadAttachmentYN", "contactName",
        "contactPhone", "highPriority", "explanationPriority", "descriptionCause", "whereIsTheProblem",
        "appointmentBooking", "reportedOn","modifiedOn", "requestedCompleteOn", "state", "rentableUnit",
        "internalCoordinator"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderOverViewDTO
{   
    @JsonProperty("id")
    private String id;
    @JsonProperty("standardOrderId")
    private String standardOrderId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("propertyId")
    private String propertyId;
    @JsonProperty("uploadAttachmentYN")
    private String uploadAttachmentYN;
    @JsonProperty("contactName")
    private String contactName;
    @JsonProperty("contactPhone")
    private String contactPhone;
    @JsonProperty("highPriority")
    private Boolean highPriority;
    @JsonProperty("explanationPriority")
    private String explanationPriority;
    @JsonProperty("descriptionCause")
    private String descriptionCause;
    @JsonProperty("whereIsTheProblem")
    private String whereIsTheProblem;
    @JsonProperty("appointmentBooking")
    private Boolean appointmentBooking; 
    @JsonProperty("reportedOn")
    private String reportedOn;
    @JsonProperty("modifiedOn")
    private String modifiedOn;    
    @JsonProperty("requestedCompleteOn")
    private String requestedCompleteOn;
    @JsonProperty("state")
    private State state = new State();
    @JsonProperty("rentableUnit")
    private RentableUnit rentableUnit= new RentableUnit();
    @JsonProperty("internalCoordinator")
    private InternalCoordinator internalCoordinator = new InternalCoordinator();

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

    @JsonProperty("standardOrderId")
    public String getStandardOrderId() {
        return standardOrderId;
    }

    @JsonProperty("standardOrderId")
    public void setStandardOrderId(String standardOrderId) {
        this.standardOrderId = standardOrderId;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("propertyId")
    public String getPropertyId() {
        return propertyId;
    }

    @JsonProperty("propertyId")
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
    
    @JsonProperty("uploadAttachmentYN")
    public String getUploadAttachmentYN() {
        return uploadAttachmentYN;
    }

    @JsonProperty("uploadAttachmentYN")
    public void setUploadAttachmentYN(String uploadAttachmentYN) {
        this.uploadAttachmentYN = uploadAttachmentYN;
    }

    @JsonProperty("contactName")
    public String getContactName() {
        return contactName;
    }

    @JsonProperty("contactName")
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @JsonProperty("contactPhone")
    public String getContactPhone() {
        return contactPhone;
    }

    @JsonProperty("contactPhone")
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @JsonProperty("highPriority")
    public Boolean getHighPriority() {
        return highPriority;
    }

    @JsonProperty("highPriority")
    public void setHighPriority(Boolean highPriority) {
        this.highPriority = highPriority;
    }

    @JsonProperty("explanationPriority")
    public String getExplanationPriority() {
        return explanationPriority;
    }

    @JsonProperty("explanationPriority")
    public void setExplanationPriority(String explanationPriority) {
        this.explanationPriority = explanationPriority;
    }

    @JsonProperty("descriptionCause")
    public String getDescriptionCause() {
        return descriptionCause;
    }

    @JsonProperty("descriptionCause")
    public void setDescriptionCause(String descriptionCause) {
        this.descriptionCause = descriptionCause;
    }

    @JsonProperty("whereIsTheProblem")
    public String getWhereIsTheProblem() {
        return whereIsTheProblem;
    }

    @JsonProperty("whereIsTheProblem")
    public void setWhereIsTheProblem(String whereIsTheProblem) {
        this.whereIsTheProblem = whereIsTheProblem;
    }

    @JsonProperty("appointmentBooking")
    public Boolean getAppointmentBooking() {
        return appointmentBooking;
    }

    @JsonProperty("appointmentBooking")
    public void setAppointmentBooking(Boolean appointmentBooking) {
        this.appointmentBooking = appointmentBooking;
    }

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
    public String getmodifiedOn() {
        return modifiedOn;
    }

    @JsonProperty("modifiedOn")
    public void setmodifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @JsonProperty("requestedCompleteOn")
    public String getRequestedCompleteOn() {
        return requestedCompleteOn;
    }

    @JsonProperty("requestedCompleteOn")
    public void setRequestedCompleteOn(String requestedCompleteOn) {
        this.requestedCompleteOn = requestedCompleteOn;
    }

    @JsonProperty("state")
    public State getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(State state) {
        this.state = state;
    }

    @JsonProperty("rentableUnit")
    public RentableUnit getRentableUnit() {
        return rentableUnit;
    }

    @JsonProperty("rentableUnit")
    public void setRentableUnit(RentableUnit rentableUnit) {
        this.rentableUnit = rentableUnit;
    }

    @JsonProperty("internalCoordinator")
    public InternalCoordinator getInternalCoordinator() {
        return internalCoordinator;
    }

    @JsonProperty("internalCoordinator")
    public void setInternalCoordinator(InternalCoordinator internalCoordinator) {
        this.internalCoordinator = internalCoordinator;
    }

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "firstName",
    "lastName"
})
public class InternalCoordinator {

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

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name"
})
public class RentableUnit {

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

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name"
})
public class State {

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
