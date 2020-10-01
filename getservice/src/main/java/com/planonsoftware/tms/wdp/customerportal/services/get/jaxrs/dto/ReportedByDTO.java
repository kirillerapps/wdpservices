package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "id", "firstName", "lastName", "email", "customerId" })
    public class ReportedByDTO {

        @JsonProperty("id")
        private String id;
        @JsonProperty("firstName")
        private String firstName;
        @JsonProperty("lastName")
        private String lastName;
        @JsonProperty("email")
        private String email;
        @JsonProperty("customerId")
        private String customerId;

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

        @JsonProperty("email")
        public String getEmail() {
            return email;
        }

        @JsonProperty("email")
        public void setEmail(String email) {
            this.email = email;
        }

        @JsonProperty("customerId")
        public String getCustomerId() {
            return customerId;
        }

        @JsonProperty("customerId")
        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }
    }
